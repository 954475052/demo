package com.framework.modules.sys.controller;

import com.baomidou.mybatisplus.mapper.Condition;
import com.framework.common.utils.Constant;
import com.framework.common.utils.R;
import com.framework.modules.sys.entity.SysDeptEntity;
import com.framework.modules.sys.entity.SysUserEntity;
import com.framework.modules.sys.service.SysDeptService;
import com.framework.modules.sys.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * 部门管理
 */
@RestController
@RequestMapping("/sys/dept")
@Api(value = "/sys/dept", description = "部门管理", tags = "后台管理", produces = MediaType.APPLICATION_JSON_VALUE)
public class SysDeptController extends AbstractController {
    @Autowired
    private SysDeptService sysDeptService;
    @Autowired
    private SysUserService sysUserService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:dept:list")
    @ApiOperation(value = "部门列表", httpMethod = "POST", notes = "查询所有部门", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SysDeptEntity> list() {
        List<SysDeptEntity> deptList = sysDeptService.queryList(new HashMap<String, Object>());

        return deptList;
    }

    /**
     * 选择部门(添加、修改菜单)
     */
    @RequestMapping("/select")
    @RequiresPermissions("sys:dept:select")
    @ApiOperation(value = "选择部门", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
            response = SysDeptEntity.class, notes = "添加、修改部门的时候，选择上级部门接口")
    public R select() {
        List<SysDeptEntity> deptList = sysDeptService.queryList(new HashMap<String, Object>());
        // 添加一级部门
        if (getUserId() == Constant.SUPER_ADMIN) {
            SysDeptEntity root = new SysDeptEntity();
            root.setDeptId(0L);
            root.setName("一级部门");
            root.setParentId(-1L);
            root.setOpen(true);
            deptList.add(root);
        }

        return R.ok().put("deptList", deptList);
    }

    /**
     * 上级部门Id(管理员则为0)
     */
    @RequestMapping("/info")
    @RequiresPermissions("sys:dept:list")
    public R info() {
        long deptId = 0;
        if (getUserId() != Constant.SUPER_ADMIN) {
            List<SysDeptEntity> deptList = sysDeptService.queryList(new HashMap<String, Object>());
            Long parentId = null;
            for (SysDeptEntity sysDeptEntity : deptList) {
                if (parentId == null) {
                    parentId = sysDeptEntity.getParentId();
                    continue;
                }

                if (parentId > sysDeptEntity.getParentId().longValue()) {
                    parentId = sysDeptEntity.getParentId();
                }
            }
            deptId = parentId;
        }

        return R.ok().put("deptId", deptId);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{deptId}")
    @RequiresPermissions("sys:dept:info")
    @ApiOperation(value = "根据部门信息", httpMethod = "POST", notes = "查询部门信息", produces = MediaType.APPLICATION_JSON_VALUE)
    public R info(@ApiParam(value = "部门id", name = "deptId", required = true) @PathVariable("deptId") Long deptId) {
        SysDeptEntity dept = sysDeptService.selectById(deptId);

        return R.ok().put("dept", dept);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:dept:save")
    public R save(@RequestBody SysDeptEntity dept) {
        sysDeptService.insert(dept);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:dept:update")
    public R update(@RequestBody SysDeptEntity dept) {
        sysDeptService.updateById(dept);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:dept:delete")
    public R delete(long deptId) {
        // 判断是否有子部门
        List<Long> deptList = sysDeptService.queryDetpIdList(deptId);
        if (deptList.size() > 0) {
            return R.error("请先删除子部门");
        }
        // 判断部门内是否有成员
        List<SysUserEntity> userList = sysUserService.selectList(Condition.create().eq("dept_id", deptId));
        if (!CollectionUtils.isEmpty(userList)) {
            return R.error("部门内有成员不允许删除");
        }
        sysDeptService.deleteById(deptId);
        return R.ok();
    }

}

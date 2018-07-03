package com.framework.modules.sys.controller;

import com.framework.common.utils.PageUtils;
import com.framework.common.utils.R;
import com.framework.common.validator.ValidatorUtils;
import com.framework.modules.sys.entity.EstHeadDataEntity;
import com.framework.modules.sys.service.EstHeadDataService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * @author MIT
 * @email framework@gmail.com
 * @date 2018-06-26 14:15:09
 */
@RestController
@RequestMapping("sys/estheaddata")
public class EstHeadDataController {
    @Autowired
    private EstHeadDataService estHeadDataService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:estheaddata:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = estHeadDataService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:estheaddata:info")
    public R info(@PathVariable("id") Long id) {
        EstHeadDataEntity estHeadData = estHeadDataService.selectById(id);

        return R.ok().put("estHeadData", estHeadData);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:estheaddata:save")
    public R save(@RequestBody EstHeadDataEntity estHeadData) {
        estHeadDataService.insert(estHeadData);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:estheaddata:update")
    public R update(@RequestBody EstHeadDataEntity estHeadData) {
        ValidatorUtils.validateEntity(estHeadData);
        estHeadDataService.updateAllColumnById(estHeadData);//全部更新

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:estheaddata:delete")
    public R delete(@RequestBody Long[] ids) {
        estHeadDataService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}

package com.framework.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.framework.common.utils.PageUtils;
import com.framework.modules.sys.entity.SysDictEntity;

import java.util.Map;

/**
 * 数据字典
 */
public interface SysDictService extends IService<SysDictEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

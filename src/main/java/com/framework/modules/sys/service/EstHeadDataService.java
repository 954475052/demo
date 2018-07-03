package com.framework.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.framework.common.utils.PageUtils;
import com.framework.modules.sys.entity.EstHeadDataEntity;

import java.util.Map;

/**
 * @author MIT
 * @email framework@gmail.com
 * @date 2018-06-26 14:15:09
 */
public interface EstHeadDataService extends IService<EstHeadDataEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


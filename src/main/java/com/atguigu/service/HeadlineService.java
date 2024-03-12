package com.atguigu.service;

import com.atguigu.pojo.Headline;
import com.atguigu.pojo.PortalVo;
import com.atguigu.utils.Result;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author fourzsh
* @description 针对表【news_headline】的数据库操作Service
* @createDate 2024-03-03 11:26:40
*/
public interface HeadlineService extends IService<Headline> {

    public Result findNewPage(PortalVo portalVo);

    public Result showHeadlineDetail(Integer hid);

    public Result publish(Headline headline);

    public Result findHeadlineByHid(Integer hid);

    public Result updateHeadLine(Headline headline);
}

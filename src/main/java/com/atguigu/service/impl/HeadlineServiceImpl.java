package com.atguigu.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.atguigu.pojo.PortalVo;
import com.atguigu.utils.Result;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.pojo.Headline;
import com.atguigu.service.HeadlineService;
import com.atguigu.mapper.HeadlineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
* @author fourzsh
* @description 针对表【news_headline】的数据库操作Service实现
* @createDate 2024-03-03 11:26:40
*/
@Service
public class HeadlineServiceImpl extends ServiceImpl<HeadlineMapper, Headline>
    implements HeadlineService{

    @Autowired
    public HeadlineMapper headlineMapper;


    @Override
    public Result findNewPage(PortalVo portalVo){
//        条件
        LambdaQueryWrapper<Headline> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(!StringUtils.isEmpty(portalVo.getKeyWords()), Headline::getTitle, portalVo.getKeyWords())
                .eq(portalVo.getType() != null, Headline::getType, portalVo.getType());

        IPage<Headline> page = new Page<>(portalVo.getPageNum(), portalVo.getPageSize());

        headlineMapper.selectPageMap(page, portalVo);

        Map<String, Object> pageInfo = new HashMap<>();
        pageInfo.put("pageData", page.getRecords());
        pageInfo.put("pageNum", page.getCurrent());
        pageInfo.put("pageSize",page.getSize());
        pageInfo.put("totalPage",page.getPages());
        pageInfo.put("totalSize",page.getTotal());

        Map<String, Object> pageInfoMap = new HashMap<>();
        pageInfoMap.put("pageInfo", pageInfo);
        return Result.ok(pageInfoMap);
    }

    @Override
    public Result showHeadlineDetail(Integer hid) {
        Map headLineDetail = headlineMapper.selectDetailMap(hid);

        Headline headline = new Headline();
        headline.setHid(hid);
        headline.setPageViews((Integer) headLineDetail.get("pageViews")+1);
        headline.setVersion((Integer) headLineDetail.get("version"));
        headlineMapper.updateById(headline);

        Map<String, Object> pageInfoMap = new HashMap<>();
        pageInfoMap.put("headline", headLineDetail);
        return Result.ok(pageInfoMap);
    }

    @Override
    public Result publish(Headline headline) {
        headline.setCreateTime(new Date());
        headline.setUpdateTime(new Date());
        headline.setPageViews(0);
        headlineMapper.insert(headline);
        return Result.ok(null);
    }

    @Override
    public Result findHeadlineByHid(Integer hid) {

        Headline headline = headlineMapper.selectById(hid);

        Map<String, Object> pageInfoMap = new HashMap<>();
        pageInfoMap.put("headline", headline);
        return Result.ok(pageInfoMap);
    }

    @Override
    public Result updateHeadLine(Headline headline) {
        Integer version = headlineMapper.selectById(headline.getHid()).getVersion();
        headline.setVersion(version);
        headline.setUpdateTime(new Date());
        headlineMapper.updateById(headline);
        return Result.ok(null);
    }

}





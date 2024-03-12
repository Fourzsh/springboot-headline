package com.atguigu.mapper;

import com.atguigu.pojo.Headline;
import com.atguigu.pojo.PortalVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
* @author fourzsh
* @description 针对表【news_headline】的数据库操作Mapper
* @createDate 2024-03-03 11:26:40
* @Entity com.atguigu.pojo.Headline
*/
public interface HeadlineMapper extends BaseMapper<Headline> {

    public IPage<Map> selectPageMap(IPage<Headline> page, @Param("portalVo") PortalVo portalVo);

    public Map selectDetailMap(Integer hid);

}





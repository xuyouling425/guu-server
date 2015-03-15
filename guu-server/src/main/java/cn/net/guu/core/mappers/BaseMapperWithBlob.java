package cn.net.guu.core.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * 抽象出代码大字段的 基本接口
* <p>Title: BaseMapperWithBlob</p>
* <p>Description: </p>
* <p>Company: www.guu.net.cn</p>
* @author xurz
* @date 2015年3月15日
 */
public interface BaseMapperWithBlob extends BaseMapper
{
    List<?> selectByExampleWithBLOBs(BaseExample example);
  
    int updateByExampleWithBLOBs(@Param("record") Object record, @Param("example") BaseExample example);

    int updateByPrimaryKeyWithBLOBs(Object record);
}

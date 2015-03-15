package cn.net.guu.core.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.net.guu.core.mappers.BaseExample;

/**
 * 抽象出带大字段的接口
 * <p>
 * Title: BaseServiceWithBlob
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: www.guu.net.cn
 * </p>
 * 
 * @author xurz
 * @date 2015年3月15日
 */
public interface BaseServiceWithBlob extends BaseService
{

	/**
	 * 通过条件查询结果集包含有大字段的查询方法
	 * <p>
	 * Title: selectByExampleWithBLOBs
	 * </p>
	 * 
	 * @param example
	 * @return
	 */
	List<?> selectByExampleWithBLOBs(BaseExample example) throws SQLException;

	/**
	 * 通过条件批量更新（包含大字段）
	 * <p>
	 * Title: updateByExampleWithBLOBs
	 * </p>
	 * 
	 * @param record
	 * @param example
	 *            更新条件
	 * @return
	 */
	int updateByExampleWithBLOBs(@Param("record") Object record, @Param("example") BaseExample example) throws SQLException;

	/**
	 * 通过主键更新（包含更新大字段）
	 * <p>
	 * Title: updateByPrimaryKeyWithBLOBs
	 * </p>
	 * 
	 * @param record
	 * @return
	 */
	int updateByPrimaryKeyWithBLOBs(Object record) throws SQLException;
}

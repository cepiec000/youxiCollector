package com.seven.collector.mapper;

import com.seven.collector.bean.GameImageDO;

import java.util.List;

/**
 *  数据层
 * 
 * @author chendongdong
 * @date 2020-05-15
 */
public interface GameImageMapper 
{
	/**
     * 查询信息
     * 
     * @param id ID
     * @return 信息
     */
	public GameImageDO selectGameImageById(Long id);
	
	/**
     * 查询列表
     * 
     * @param gameImage 信息
     * @return 集合
     */
	public List<GameImageDO> selectGameImageList(GameImageDO gameImage);
	
	/**
     * 新增
     * 
     * @param gameImage 信息
     * @return 结果
     */
	public int insertGameImage(GameImageDO gameImage);
	
	/**
     * 修改
     * 
     * @param gameImage 信息
     * @return 结果
     */
	public int updateGameImage(GameImageDO gameImage);
	
	/**
     * 删除
     * 
     * @param id ID
     * @return 结果
     */
	public int deleteGameImageById(Long id);
	
	/**
     * 批量删除
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteGameImageByIds(String[] ids);
	
}
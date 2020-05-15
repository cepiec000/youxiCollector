package com.seven.collector.mapper;

import com.seven.collector.bean.GameInfoDO;

import java.util.List;

/**
 *  数据层
 * 
 * @author chendongdong
 * @date 2020-05-15
 */
public interface GameInfoMapper 
{
	/**
     * 查询信息
     * 
     * @param id ID
     * @return 信息
     */
	public GameInfoDO selectGameInfoById(Long id);
	
	/**
     * 查询列表
     * 
     * @param gameInfo 信息
     * @return 集合
     */
	public List<GameInfoDO> selectGameInfoList(GameInfoDO gameInfo);
	
	/**
     * 新增
     * 
     * @param gameInfo 信息
     * @return 结果
     */
	public int insertGameInfo(GameInfoDO gameInfo);
	
	/**
     * 修改
     * 
     * @param gameInfo 信息
     * @return 结果
     */
	public int updateGameInfo(GameInfoDO gameInfo);
	
	/**
     * 删除
     * 
     * @param id ID
     * @return 结果
     */
	public int deleteGameInfoById(Long id);
	
	/**
     * 批量删除
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteGameInfoByIds(String[] ids);
	
}
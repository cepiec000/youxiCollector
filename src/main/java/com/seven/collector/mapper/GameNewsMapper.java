package com.seven.collector.mapper;

import com.seven.collector.bean.GameNewsDO;

import java.util.List;

/**
 *  数据层
 * 
 * @author chendongdong
 * @date 2020-05-18
 */
public interface GameNewsMapper 
{

	public GameNewsDO selectByNewsId(Long newsId);
	/**
     * 查询信息
     * 
     * @param id ID
     * @return 信息
     */
	public GameNewsDO selectGameNewsById(Long id);
	
	/**
     * 查询列表
     * 
     * @param gameNews 信息
     * @return 集合
     */
	public List<GameNewsDO> selectGameNewsList(GameNewsDO gameNews);
	
	/**
     * 新增
     * 
     * @param gameNews 信息
     * @return 结果
     */
	public int insertGameNews(GameNewsDO gameNews);
	
	/**
     * 修改
     * 
     * @param gameNews 信息
     * @return 结果
     */
	public int updateGameNews(GameNewsDO gameNews);
	
	/**
     * 删除
     * 
     * @param id ID
     * @return 结果
     */
	public int deleteGameNewsById(Long id);
	
	/**
     * 批量删除
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteGameNewsByIds(String[] ids);
	
}
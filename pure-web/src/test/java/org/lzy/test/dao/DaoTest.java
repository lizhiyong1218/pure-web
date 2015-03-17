package org.lzy.test.dao;
/**
 * 使用测试的时候,要将原来带的java ee5给remove掉,换成uer library j2ee
 * defaultRollback=true 不会往数据库中插入数据
 */

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.lzy.api.model.Channel;
import org.lzy.api.model.Classify;
import org.lzy.api.model.Media;
import org.lzy.core.common.Pagination;
import org.lzy.core.common.QueryCondition;
import org.lzy.core.service.IBaseService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml",
		"classpath:dispatcher-servlet.xml" 
})
@TransactionConfiguration(transactionManager="txManager",defaultRollback=false)
public class DaoTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Resource(name="baseService")
	private IBaseService baseService;
	
	/**
	 * 增
	 */
	@Test
	public void testSave() {
		Channel channel=new Channel();
		channel.setName("123");
		channel.setCruser("lzy");
		baseService.save(channel);
		System.out.println(1);
	}
	
	/**
	 * 批量增加同类型对象
	 */
	@Test
	public void testbatchSave() {
		Channel channel=baseService.getById(Channel.class, (long)1);
		Classify classify=baseService.getById(Classify.class, (long)1);
		
		List<Media> ens=new ArrayList<Media>();
		for(int i=0;i<10;i++){
			Media media=new Media();
			media.setTitle("M"+i);
			media.setChannel(channel);
			media.setClassify(classify);
			ens.add(media);
		}
		baseService.batchSave(ens);
	}
	
	/**
	 * 批量增加不同类型对象
	 */
	@Test
	public void testBatchSave() {
		Channel channel=new Channel();
		channel.setName("新闻");
		channel.setCruser("lzy");
		Classify classify=new Classify();
		classify.setName("国内");
		classify.setCruser("lzy");
		List<Object> objects=new ArrayList<Object>();
		objects.add(channel);
		objects.add(classify);
		baseService.batchSave(objects);
	}
	
	/**
	 * 删
	 */
	@Test
	public void testDelete(){
		System.out.println(baseService.delete(Classify.class, (long)10));
	}
	
	/**
	 * 通过ID批量删除
	 */
	@Test
	public void testBatchDelete(){
		Object[] ids={(long)153,(long)154};
		System.out.println(baseService.batchDelete(Media.class, ids));
	}
	
	/**
	 * 改
	 */
	@Test
	public void testUpdate(){
		Media media=baseService.getById(Media.class, (long)155);
		media.setTitle("新修改!");
		baseService.update(media);
	}
	
	/**
	 * 批量修改
	 */
	public void testBatchUpdate(){
		String jpql="update Media m set m.status = 10 where  m.id in ('156','157')";
		System.out.println("共修改"+baseService.executeJpql(jpql)+"条数据");
	}
	
	/**
	 * 查询所有
	 */
	@Test
	public void testGetAll(){
		List<Media> list= baseService.getAll(Media.class);
		for (Media media : list) {
			System.out.println("title:"+media.getTitle());
		}
	}
	
	/**
	 * 同过ID批量查
	 */
	@Test
	public void testGetByIds(){
		Object[] ids={(long)155,(long)156};
		List<Media> list= baseService.getByIds(Media.class, ids);
		for (Media media : list) {
			System.out.println("title:"+media.getTitle());
		}
	}
	
	/**
	 * 查询分页对象
	 */
	@Test
	public void testGetPagination(){
		List<QueryCondition> queryConditions=new ArrayList<QueryCondition>();
		queryConditions.add(new QueryCondition("title", QueryCondition.LK, "M"));
		//和上面写法一样
//		String customJPQL="o.title like '%M%'"; 
//		queryConditions.add(new QueryCondition(customJPQL));
		String orderBy="order by id desc";
		Pagination<Media> pagination= baseService.getPagination(Media.class, queryConditions, orderBy, 1, 2);
		System.out.println("size:"+pagination.getRecordList().size());
	}
	
	/**
	 * 通过条件查询单个对象
	 */
	@Test
	public void testGetSingleResult(){
		List<QueryCondition> queryConditions=new ArrayList<QueryCondition>();
		queryConditions.add(new QueryCondition("id", QueryCondition.EQ, (long)156));
		Media media=(Media)baseService.getSingleResult(Media.class, queryConditions);
		System.out.println("id:"+media.getId());
	}
	
	/**
	 * 通过jpql查询单个对象
	 */
	@Test
	public void testGetSingleResultByJpql(){
		//要加上select m不然会报错
		String jpql="select m from Media m join m.channel c where c.name=? and m.title=?";
		Media media=(Media)baseService.getSingleResultByJpql(jpql,"新闻","M3");
		System.out.println("id:"+media.getId());
	}
	
	/**
	 * 查询所有符合条件的对象
	 */
	@Test
	public void testGetlistbyconditions(){
		List<QueryCondition> queryConditions=new ArrayList<QueryCondition>();
		queryConditions.add(new QueryCondition("title", QueryCondition.LK, "M"));
		List<Media> list=baseService.getListByconditions(Media.class, queryConditions);
		System.out.println("size:"+list.size());
	}
	
	/**
	 * 查询所有符合条件的对象
	 * 排序
	 */
	@Test
	public void testGetlistbyconditions2(){
		List<QueryCondition> queryConditions=new ArrayList<QueryCondition>();
		queryConditions.add(new QueryCondition("title", QueryCondition.LK, "M"));
		String orderBy="order by id desc ";
		List<Media> list=baseService.getListByConditions(Media.class, queryConditions, orderBy);
		System.out.println("size:"+list.size());
	}
	
	/**
	 * 查询所有符合条件的对象
	 * 排序
	 * 分页
	 */
	@Test
	public void testGetlistbyconditions3(){
		List<QueryCondition> queryConditions=new ArrayList<QueryCondition>();
		queryConditions.add(new QueryCondition("title", QueryCondition.LK, "M"));
		int pageNow=1;
		int pageSize=10;
		String orderBy="order by id desc ";
		List<Media> list=baseService.getListByConditions(Media.class, queryConditions, orderBy,pageNow,pageSize);
		System.out.println("size:"+list.size());
	}
	
	/**
	 * 根据jpql查询列表
	 */
	@Test
	public void testGetlistbyjpql(){
		String jpql="from Media m join m.channel c where c.name=?";
		List<Media> list=baseService.getListByJpql(jpql, "新闻");
		System.out.println("size:"+list.size());
	}
	
	/**
	 * 根据jpql查询列表
	 * 分页
	 */
	@Test
	public void testGetlistbyjpql2(){
		String jpql="from Media m join m.channel c where c.name=?";
		int pageNow=1;
		int pageSize=10;
		List<Media> list=baseService.getListByJpql(Media.class,pageNow,pageSize,jpql, "新闻");
		System.out.println("size:"+list.size());
	}
	
	/**
	 * 执行jpql语句
	 */
	@Test
	public void testExecuteJpql(){
		String jpql="update Media m set m.status = 10 where  m.id in ('15','15')";
		System.out.println("共修改"+baseService.executeJpql(jpql)+"条数据");
	}
	
	/**
	 * 测试多表查询
	 */
	@Test
	public void testmultitablequery(){
		String jpql="select m from Media m join m.channel ch join m.classify lx  where ch.id=? and lx.id=?";
		List<Media> mList= baseService.getListByJpql(Media.class,1, 10, jpql, (long)13,(long)1);
		System.out.println("size:!!!!!!!!!!!!!!!!!!!!!!!!!!!"+mList.size());
	}
	
}

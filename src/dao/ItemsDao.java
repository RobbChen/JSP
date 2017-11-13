package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Items;
import util.DBUtil;

public class ItemsDao {
	//获取所有的商品信息
	public ArrayList<Items> getAllItems(){
		ArrayList<Items> result=new ArrayList<>();
		Connection conn=DBUtil.getConnection();
	
		String sql="select *from items ;";
		try {
			PreparedStatement ptmt=conn.prepareStatement(sql);
			ResultSet rs=ptmt.executeQuery();
			while(rs.next()){
				Items item=new Items();
				item.setId(rs.getInt("id"));
				item.setName(rs.getString("name"));
				item.setCity(rs.getString("city"));
				item.setNumber(rs.getInt("number"));
				item.setPrice(rs.getInt("price"));
				item.setPicture(rs.getString("picture"));
				result.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return result;			
	}
	//根据商品编号获得商品资料
	public Items getItemsById(int id)throws Exception{
		Items item=new Items();
		String sql="select *from items where id=?;";
		Connection conn=DBUtil.getConnection();
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setInt(1, id);
		ResultSet rs=ptmt.executeQuery();
		if(rs.next()){
			item.setId(rs.getInt("id"));
			item.setName(rs.getString("name"));
			item.setCity(rs.getString("city"));
			item.setNumber(rs.getInt("number"));
			item.setPicture(rs.getString("picture"));
			item.setPrice(rs.getInt("price"));
		}
		return item;
	}
	//获取最近浏览的前五条信息
	public ArrayList<Items> getViewList(String list) throws Exception{
		ArrayList<Items> result=new ArrayList<>();
		int iCount=5;
		if(list!=null&&list.length()>0){
			String [] arr=list.split(",");
			if(arr.length>=5){
				for(int i=arr.length-1;i>=arr.length-iCount;i--){
					result.add(getItemsById(Integer.parseInt(arr[i])));
				}
			}else{
				for(int i=arr.length-1;i>=0;i--){
					result.add(getItemsById(Integer.parseInt(arr[i])));
				}
			}
			return result;
		}
		else{
			return null;
		}
	}
}

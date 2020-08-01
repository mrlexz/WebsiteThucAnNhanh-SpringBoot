package com.example.demo.service;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.demo.model.SanPham;

public class SanPhamServiceImpl implements SanPhamService {

	@Override
	public ArrayList<SanPham> timSanPhamtheoTen() {
		return null;
	}
//	@Override
//	 public void update(SanPham object) {
//		        Session session = HibernateUtil.getSessionFactory().openSession();
//		        Transaction transaction = null;
//		        try {
//		            transaction = session.beginTransaction();
//		            session.update(object);
//		            transaction.commit();
//		        } catch (Exception ex) {
//		            if (transaction != null) {
//		                transaction.rollback();
//		            }
//		            ex.printStackTrace();
//		        } finally {
//		            session.flush();
//		            session.close();
//		        }
//		    }
	
}

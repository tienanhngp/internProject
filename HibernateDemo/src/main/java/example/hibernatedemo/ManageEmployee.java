package example.hibernatedemo;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ManageEmployee {

    // TẠO EMPLOYEE
    public void addEmployee(Employee employee){
        Session session = HibernateUtil.getFactory().openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.save(employee);
            employee.setSalary(10/ employee.getSalary());
            transaction.commit();
        }catch (Exception e) {
            if (transaction!=null) transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
    // LẤY TẤT CẢ EMPLOYEES
    public List listEmployees( ){
        Session session = HibernateUtil.getFactory().openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            List employees = session.createQuery("FROM Employee").list();
            transaction.commit();
            return employees;
        }catch (HibernateException e) {
            if (transaction!=null) transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }
    // XÓA TẤT CẢ EMPLOYEES
    public void deleteAllEmployees(){
        Session session = HibernateUtil.getFactory().openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.createSQLQuery("TRUNCATE  table Employee").executeUpdate();
            transaction.commit();
        }catch (HibernateException e){
            if (transaction!=null) transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
}

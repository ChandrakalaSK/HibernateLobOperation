package in.ineuron.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import in.ineuron.Model.JobSeeker;
import in.ineuron.Util.HibernateUtil;

public class InsertApp {

	public static void main(String[] args) throws IOException {
		
		Session session=null;
		Transaction transaction=null;
		boolean flag = false;
		byte[] photo = null;
		char[] resume = null;
		File f = null;
		Integer id = null;
		
		//logic for copying the image data to byte[]
		try(FileInputStream fis = new FileInputStream("D:\\images\\IMG_20200201_185813_685 (1).jpg")) {
			
			photo = new byte[fis.available()];
			fis.read();
		}
		
		//logic for copying the resume data to character array
		try
		{
			f = new File("D:\\images\\resume.txt");
			try(FileReader fr = new FileReader(f)){
				resume = new char[(int) f.length()];
				fr.read(resume);
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		try {
		session=HibernateUtil.getSession();
		
		if(session !=null)
		{
			transaction = session.beginTransaction();
			if(transaction != null)
			{
				JobSeeker jobSeeker = new JobSeeker();
				jobSeeker.setJsName("chandrakala");
				jobSeeker.setJsAddr("Bengaluru");
				jobSeeker.setPhoto(photo);
				jobSeeker.setResume(resume);
			id	= (Integer) session.save(jobSeeker);
				
				flag=true;
				
			}
			
		}
		}catch (HibernateException e) {
			e.printStackTrace();
			
		}catch (Exception e) {
			e.printStackTrace();
			
		}finally {
			if(flag) {
				transaction.commit();
			System.out.println("Object saved to database");
			
			
			}
			else {
				transaction.rollback();
				System.out.println("Object not saved to database");
			
			}
			
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}

	}

}

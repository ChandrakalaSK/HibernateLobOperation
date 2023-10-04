package in.ineuron.Test;

import java.io.FileOutputStream;
import java.io.FileWriter;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import in.ineuron.Model.JobSeeker;
import in.ineuron.Util.HibernateUtil;

public class SelectRecordApp {
	public static void main(String[] args)
	{
		Session session = null;
		JobSeeker seeker = null;
		int id = 1;
		try {
			session = HibernateUtil.getSession();
			if(session != null)
			{
				 seeker = session.get(JobSeeker.class,id);
			}
			if (seeker != null) {
				System.out.println("Id is :: "+seeker.getJsId());
				System.out.println("Id is :: "+seeker.getJsName());
				System.out.println("Id is :: "+seeker.getJsAddr());
				try(FileOutputStream fos = new FileOutputStream("./store/IMG_20200201_185813_685 (1).jpg")) {
					fos.write(seeker.getPhoto());
					try(FileWriter fis = new  FileWriter("./store/resume.txt"))
					{
						fis.write(seeker.getResume());
					}
				}
				
			}
			else
			{
				System.out.println("Record not available for given id ::"+id);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}
		
	}

}

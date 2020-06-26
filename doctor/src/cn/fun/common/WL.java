package cn.fun.common;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.fun.entity.PlaceUse;
import cn.fun.service.BizService;
import util.Constant;

public class WL implements ServletContextListener {

	public static BizService service;

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(event.getServletContext());
		BizService service = (BizService) wac.getBean("bizService");
		WL.service = service;
		new Thread(new CarThread(event.getServletContext())).start();
	}

}

class CarThread implements Runnable {
	ServletContext context;

	public CarThread(ServletContext context) {
		this.context = context;
	}

	@Override
	public void run() {
		while (true) {
			try {
				List<PlaceUse> plist = (List<PlaceUse>) context.getAttribute(Constant.IN_QUEUE);
				if (plist != null && plist.size()>0) {
					System.out.println("等待数量: " + plist.size()); 
					PlaceUse pu = plist.get(0);
					boolean p = WL.service.addInList(pu);
					if (p) {
						plist.remove(0);
					}
				}
			} catch (Exception e1) {
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}

	}

}

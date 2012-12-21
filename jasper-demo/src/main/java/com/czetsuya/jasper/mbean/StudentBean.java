package com.czetsuya.jasper.mbean;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import com.czetsuya.jasper.edition.StudentReportBean;
import com.czetsuya.jasper.model.Student;

/**
 * @author Edward P. Legaspi
 * @since Dec 17, 2012
 */
@Named
@RequestScoped
public class StudentBean {
	public void run() throws JRException, IOException {
		Collection<Student> students = StudentReportBean.createBeanCollection();
		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(
				students, false);

		String reportPath = FacesContext.getCurrentInstance().getExternalContext()
				.getRealPath("/WEB-INF/classes/StudentReport.jasper");

		JasperReport jasperReport;
		jasperReport = (JasperReport) JRLoader.loadObjectFromFile(reportPath);

		Map<String, Object> params = new HashMap<String, Object>();
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params,
				beanCollectionDataSource);

		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance()
				.getExternalContext().getResponse();
		ServletOutputStream out = response.getOutputStream();		

		response.setContentType("application/pdf");
		response.setHeader("Content-disposition", "attachment; filename=student-report.pdf");
		
		JasperExportManager.exportReportToPdfStream(jasperPrint, out);

		out.flush();

		out.close();
	}
}

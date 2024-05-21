package Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Pages.APIPage;
import Pages.POJO.placeID;
import io.restassured.response.Response;
import org.jdom2.JDOMException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Objects.ProductVerificationObject;
import Pages.ExcelPage;
import Pages.ProductCheckoutPage;
import Pages.ProductVerficiationPage;
import TestRunner.TestRunner;
import Tests.Hooks;
import io.cucumber.java.Scenario;

public class TestContext {


	public WebDriver driver;
	
	//utils
	public Basepage bp;
	public TestData td;
	public XMLReader objXMLReader;
	public sql sql;
	
	//Test
	public Hooks hooks;

	//POJO
	public placeID json;

	//Response
	public Response resp;
	
	//reports
	public ExtentSparkReporter extentReporter;
	public ExtentReports extent;
	public ExtentTest test;

	//Pages
	public ProductVerficiationPage pp;
	public ProductCheckoutPage pc;
	public ExcelPage e;
	public APIPage api;
	
	//extent	
	public ExtentTest ProductVerification_test;	
	public static ExtentSparkReporter ProductVerification_extentReporter;
	public static ExtentReports ProductVerification_extent;
	
	public ExtentTest ProductCheckout_test;	
	public static ExtentSparkReporter ProductCheckout_extentReporter;
	public static ExtentReports ProductCheckout_extent;
	
	public ExtentTest Excel_test;	
	public static ExtentSparkReporter Excel_extentReporter;
	public static ExtentReports Excel_extent;

	public ExtentTest API_test;
	public static ExtentSparkReporter API_extentReporter;
	public static ExtentReports API_extent;
	
	
	public TestContext() throws IOException, JDOMException
	{		
		this.hooks = new Hooks(this);
		this.td = new TestData();
		this.objXMLReader = new XMLReader();
		this.sql = new sql();

		//POJO
		this.json = new placeID();

		//Response
		//this.resp=null;

		//utils
		bp = PageFactory.initElements(hooks.launchDriver(), Basepage.class);
		
		pp=new ProductVerficiationPage(hooks.launchDriver());		
		pc=new ProductCheckoutPage(hooks.launchDriver());
		e=new ExcelPage(hooks.launchDriver());
		api = new APIPage(hooks.launchDriver());
		
		
		//pages 
		//pp = PageFactory.initElements(hooks.launchDriver(), ProductPage.class);
	}
	

}

package designAcc.designAcc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import BusinessMethods.Login_Page;

public class ExcelWriter {

	static int newFlag = 0;
	static int createsheet = 0;
	static XSSFWorkbook wwb;
	static XSSFSheet sheet,sheet1;
	static File filepath;
	static File file;

	// static WebDriver driver;
	public static Login_Page log = new Login_Page();
	
	//Method to Create Workbook and to enter the header values
	public static void designAccelator(WebDriver driver) throws Exception {
		// take this to design accelerator config file
		filepath = new File(
				"C:\\Users\\daisymanik.MAVERICSYSTEMS\\Asset\\Data_Generator-master\\maven\\Datatable\\accelator.xlsx");
		if (filepath.exists() || newFlag == 0) {
			CreateWorkbook();
			newFlag = 1;
		}

		headerUpdateWorkbook();

		List<WebElement> linkElements3 = driver.findElements(By.tagName("select"));
		for (int i = 0; i < linkElements3.size(); i++) {
			System.out.println(i + "select size");
			generatelocators("select", linkElements3.get(i));
		}

		List<WebElement> linkElements5 = driver.findElements(By.tagName("button"));
		for (int i = 0; i < linkElements5.size(); i++) {
			System.out.println(i + "button size");
			generatelocators("Button", linkElements5.get(i));
		}

		StringBuilder id = null;

		List<WebElement> linkElements = driver.findElements(By.tagName("input"));
		for (int i = 0; i < linkElements.size(); i++) {

			String sAttribute = linkElements.get(i).getAttribute("type");
			String elementValue = linkElements.get(i).getAttribute("value");
			if (elementValue.equals("select")) {
				System.out.println("*************************Input Type Select*********************************");
				generatelocators("select", linkElements.get(i));
			} else {

				switch (sAttribute) {
				case "text":
					System.out.println("*************************Input Type Select*********************************");
					generatelocators("text", linkElements.get(i));
					break;

				case "button":
					System.out.println("*************************Input Type Button*********************************");
					generatelocators("Button", linkElements.get(i));
					break;

				case "radio":
					System.out.println("*************************Input Type Radio*********************************");
					generatelocators("Radio", linkElements.get(i));
					break;

				case "checkbox":
					System.out.println("*************************Input Type CheckBox*********************************");
					generatelocators("checkbox", linkElements.get(i));
					break;

				case "file":
					System.out.println("*************************Input Type File*********************************");
					generatelocators("file", linkElements.get(i));
					break;

				case "img":
					System.out.println("*************************Input Type Img*********************************");
					generatelocators("img", linkElements.get(i));
					break;

				default:
					break;
				}
			}
		}
	}
    
	//Method to Generate Locators
	public static void generatelocators(String tag, WebElement element) throws Exception {

		StringBuilder placeHolder = new StringBuilder();
		StringBuilder id = new StringBuilder();
		StringBuilder name = new StringBuilder();
		StringBuilder classname = new StringBuilder();
		StringBuilder fieldvalue = new StringBuilder();
		StringBuilder title = new StringBuilder();

		StringBuilder place = placeHolder.append(element.getAttribute("placeholder"));
		System.out.println(place);
		StringBuilder idd = id.append(element.getAttribute("id"));
		System.out.println(idd);
		StringBuilder namee = name.append(element.getAttribute("name"));
		System.out.println(namee);
		StringBuilder classs = classname.append(element.getAttribute("class"));
		System.out.println(classs);
		StringBuilder valuee = fieldvalue.append(element.getAttribute("value"));
		System.out.println(valuee);
		StringBuilder titlee = title.append(element.getAttribute("title"));
		System.out.println(titlee);
		System.out.println("Entered fetching Select objects");
		String xpath = xpathGenerator(element, tag);
		String FieldText = xpath.substring(xpath.indexOf("~$") + 2);
		xpath = xpath.substring(0, xpath.indexOf("~$"));
		if (tag.equals("button")) {
			FieldText = fieldvalue.toString();
		}

		dataUpdateWorkbook(FieldText, tag, id.toString(), name.toString(), "", classname.toString(), "", "",
				title.toString(), "", xpath);
	}

	// Create a new Workbook
	public static void CreateWorkbook() {
		
		if(createsheet == 0) {

		Date date = new Date() ;
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss") ;	
															
		try {
			System.out.println("Create a Workbook");
			file = new File(dateFormat.format(date) + "") ;
			FileOutputStream outStream = new FileOutputStream(filepath);
			wwb = new XSSFWorkbook();
			sheet = wwb.createSheet("Sheet1 - " + file);
			
			wwb.write(outStream);
			outStream.close();
			createsheet = 1;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		} else if (createsheet == 1) {
			sheet1 = wwb.createSheet("Sheet2 - " + file);
		}
	}

	public static void headerUpdateWorkbook() throws IOException {
		FileInputStream inputstreams = new FileInputStream(filepath);
		wwb = new XSSFWorkbook(inputstreams);
		sheet = wwb.getSheetAt(0);
		int rowCount = sheet.getLastRowNum();
		System.out.println(rowCount);

		if (rowCount < 1) {
			Row row = sheet.createRow(0);
			row.createCell(0).setCellValue("Field_Text");
			row.createCell(1).setCellValue("Field_Type");
			row.createCell(2).setCellValue("Attribute_ID");
			row.createCell(3).setCellValue("Attribute_Name");
			row.createCell(4).setCellValue("Attribute_InnerText");
			row.createCell(5).setCellValue("Attribute_Class");
			row.createCell(6).setCellValue("Attribute_Value");
			row.createCell(7).setCellValue("Attribute_Placeholder");
			row.createCell(8).setCellValue("Attribute_Title");
			row.createCell(9).setCellValue("Attribute_TextValue");
			row.createCell(10).setCellValue("Attribute_xpath");

			row.createCell(11).setCellValue("Grand_ParentFollow_sibling");
			row.createCell(12).setCellValue("Grand_Parent_sibling");
			row.createCell(13).setCellValue("Grand_Parent");
			row.createCell(14).setCellValue("Parent_sib_child");
			row.createCell(15).setCellValue("Parent_sibling");
			row.createCell(16).setCellValue("Parent");
			row.createCell(17).setCellValue("Preceding");
			row.createCell(18).setCellValue("Following");

		}

		inputstreams.close();
		FileOutputStream outStream = new FileOutputStream(filepath);
		wwb.write(outStream);
		outStream.close();
	}

	public static void dataUpdateWorkbook(String sf1, String sf2, String sf3, String sf4, String sf5, String sf6,
			String sf7, String sf8, String sf9, String sf10, String sf11) throws IOException {
		System.out.println("Xpath Value: " + sf11);
		System.out.println("Sample");
		FileInputStream inputstreams = new FileInputStream(filepath);
		wwb = new XSSFWorkbook(inputstreams);
		sheet = wwb.getSheetAt(0);
		int rowCount = sheet.getLastRowNum();
		System.out.println(rowCount);
		if ((sf1 == null || sf1.equals("") || sf1.equals("null")) && !(sf3.equals(""))) {
			sf1 = sf3;
		} else if ((sf1 == null || sf1.equals("") || sf1.equals("null")) && !(sf4.equals(""))) {
			sf1 = sf4;
		}
		if (!sf11.contains("//")) {
			sf11 = "";
		}

		Row row = sheet.createRow(rowCount + 1);
		System.out.println(sf1);
		sf1 = sf1.replace(":", "").trim();
		sf1 = sf1.replace("*", "").trim();
		row.createCell(0).setCellValue(sf1.replaceAll(" ", "_").trim());
		row.createCell(1).setCellValue(sf2);
		row.createCell(2).setCellValue(sf3);
		row.createCell(3).setCellValue(sf4);
		row.createCell(4).setCellValue(sf5);
		row.createCell(5).setCellValue(sf6);
		row.createCell(6).setCellValue(sf7);
		row.createCell(7).setCellValue(sf8);
		row.createCell(8).setCellValue(sf9);
		row.createCell(9).setCellValue(sf10);
		row.createCell(10).setCellValue(sf11);

		inputstreams.close();
		FileOutputStream outStream = new FileOutputStream(filepath);
		wwb.write(outStream);
		outStream.close();
	}

	public static String generateXPathBuilder(String xpathValue, String xpathType, String newXpath) {
		StringBuffer attribute = new StringBuffer();
		StringBuffer label = new StringBuffer();
		StringBuffer value = new StringBuffer();
		StringBuffer fieldText = new StringBuffer();
		StringBuffer xValue = new StringBuffer();
		attribute.append(getValue(xpathValue, "Attribute"));
		label.append(getValue(xpathValue, "Label"));
		value.append(getValue(xpathValue, "Value"));
		xValue.append(generateXpath(attribute.toString(), label.toString(), value.toString()));
		newXpath = xValue + "/preceding-sibling::" + newXpath;
		System.out.println(newXpath);
		return newXpath;
	}

	@SuppressWarnings({ "null", "unused" })
	public static String xpathGenerator(WebElement linkElements, String type) {

		// TODO Auto-generated method stub
		int identifiedFlag = 0;
		String newXpath = null, tagName, att_type, att_id, att_name, att_class, att_title, att_text;
		tagName = linkElements.getTagName();
		
		att_type = linkElements.getAttribute("type");
		System.out.println(att_type);
		att_id = linkElements.getAttribute("id");
		att_name = linkElements.getAttribute("name");
		att_class = linkElements.getAttribute("class");
		att_title = linkElements.getAttribute("value");
		att_text = linkElements.getText();
		
		
		String[] strFlow = { "Following", "Preceding", "Parent", "Parent_sibling", "Parent_sib_child", "Grand_Parent",
				"Grand_Parent_sibling", "Grand_ParentFollow_sibling" };
		tagName = linkElements.getTagName();
/*
    if(tagName.equals("input")) { 
		  newXpath = tagName+"[@type='" + type + "']"; 
		  }else { 
		  newXpath = tagName; 
		  }
		
*/
		switch (tagName) {

		case "input":

			switch (type) {
			case "id":
				newXpath = "//" + tagName+"[@id='" + att_id + "']";
				System.out.println(newXpath);
				break;

			case "text":
				newXpath = "//" +tagName+"[@name='" + att_name + "']";
				System.out.println(newXpath);
				break;

			case "class":
				newXpath = "//" +tagName+"[@class='" + att_class + "']";
				System.out.println(newXpath);
				break;
				
			case "text1":
				if(!(att_class == null)){
					newXpath=null;
				}else
				{
					newXpath = "//" +tagName+"[contains(text()='" + att_text + "')]";
				}
				System.out.println(newXpath);
				break;

			case "title":
				newXpath = "//" +tagName+"[@title='" + att_title + "']";
				System.out.println(newXpath);
				break;
				
			case "Radio":
				newXpath = "//" +tagName+"[@id='" + att_id+ "']";
				System.out.println("Radio -->" + newXpath);
				break;

			case "checkbox":
				newXpath = "//" +tagName+"[@id='" + att_id+ "']";
				System.out.println("Checkbox -->" + newXpath);
				break;

			case "Button":
				//newXpath = "//" +tagName+"[contains(text()='" + att_text + "')]";
				newXpath = "//" +tagName+"[@id='" + att_id + "']";
				System.out.println(newXpath);
				break;

			default:
				System.out.println("please pass the valid type");
				break;

			}
			break;


		case "select":

			switch (type) {
			case "select":
				newXpath = "//" +tagName+"[@name='" + att_name + "']";
				System.out.println(newXpath);
				break;

			case "name":
				newXpath = "//" +tagName+"[@name='" + att_name + "']";
				System.out.println(newXpath);
				break;

			case "class":
				newXpath = "//" +tagName+"[@class='" + att_class + "']";
				System.out.println(newXpath);
				break;

			case "title":
				newXpath = "//" +tagName+"[@title='" + att_title + "']";
				System.out.println(newXpath);
				break;

			default:
				System.out.println("please pass the valid type");
				break;

			}
			break;

			
		case "Button":

			switch (type) {
			case "id":
				newXpath = "//" +tagName+"[@id='" + att_id + "']";
				System.out.println(newXpath);
				break;

			case "name":
				newXpath = "//" +tagName+"[@name='" + att_name + "']";
				System.out.println(newXpath);
				break;

			case "class":
				newXpath = "//" +tagName+"[@class='" + att_class + "']";
				System.out.println(newXpath);
				break;

			case "title":
				newXpath = "//" +tagName+"[@title='" + att_title + "']";
				System.out.println(newXpath);
				break;

			default:
				System.out.println("please pass the valid type");
				break;

			}
			break;
	
		case "img":

			switch (type) {
			case "id":
				newXpath = "//" +tagName+"[@id='" + att_id + "']";
				System.out.println(newXpath);
				break;

			case "name":
				newXpath = "//" +tagName+"[@name='" + att_name + "']";
				System.out.println(newXpath);
				break;

			case "class":
				newXpath = "//" +tagName+"[@class='" + att_class + "']";
				System.out.println(newXpath);
				break;

			case "value":
				newXpath = tagName+"[@value='" + att_type + "']";
				System.out.println(newXpath);
				break;

			case "title":
				newXpath = tagName+"[@title='" + att_title + "']";
				System.out.println(newXpath);
				break;

			case "href":
				newXpath = tagName+"[@id='" + att_type + "']";
				System.out.println(newXpath);
				break;

			default:
				System.out.println("please pass the valid type");
				break;

			}
			break;
		}
		

		String xPathValue;
		String attribute, label, value = null, fieldText = null;
		List<WebElement> preceding, Parent_Sibling, Grand_Parent_Sibling;
		WebElement Parents = null, Grand_Parents = null;
		int iCount = 0;
/*		do {
			System.out.println("###########################################################");
			System.out.println(strFlow[iCount]);
			switch (strFlow[iCount]) {

			case "Following":
				if (!(type.equals("select"))) {
					preceding = linkElements.findElements(By.xpath("following-sibling::"));
					System.out.println(preceding.size());
					if (preceding.size() > 0) {
						System.out.println("Entering into preceding");
						xPathValue = findElementsTag(preceding);
						if (xPathValue != null) {
							newXpath = generateXPathBuilder(xPathValue, "/preceding-sibling::", newXpath);
							identifiedFlag = 1;
						}
					}
				}
				break;

			case "Preceding":
				preceding = linkElements.findElements(By.xpath("preceding-sibling::*"));
				if (preceding.size() > 0) {
					System.out.println("Entering into preceding");
					xPathValue = findElementsTag(preceding);
					System.out.println("test" + xPathValue);
					if (xPathValue != null) {
						newXpath = generateXPathBuilder(xPathValue, "/following-sibling::", newXpath);
						identifiedFlag = 1;
					}
				}
				break;

			case "Parent":
				Parents = linkElements.findElement(By.xpath("parent::*"));
				tagName = Parents.getTagName();
				System.out.println("Entering into parents " + tagName);
				xPathValue = findParentTag(Parents, type);
				if (xPathValue != null) {
					newXpath = generateXPathBuilder(xPathValue, "/", newXpath);
					identifiedFlag = 1;
					System.out.println(newXpath);
				} else {
					newXpath = tagName + "/" + newXpath;
				}
				break;

			case "Parent_sibling":
				Parent_Sibling = Parents.findElements(By.xpath("preceding-sibling::*"));
				if (Parent_Sibling.size() > 0) {
					System.out.println("Entering into Parent preceding");
					xPathValue = findElementsTag(Parent_Sibling);
					System.out.println(xPathValue);
					if (xPathValue != null) {
						newXpath = generateXPathBuilder(xPathValue, "/following-sibling::", newXpath);
						identifiedFlag = 1;
						System.out.println(newXpath);
					}
				}
				break;

			case "Grand_Parent":
				Grand_Parents = Parents.findElement(By.xpath("parent::*"));
				tagName = Grand_Parents.getTagName();
				System.out.println("Entering into Grand Parents " + tagName);
				xPathValue = findParentTag(Grand_Parents, type);
				if (xPathValue != null) {
					newXpath = generateXPathBuilder(xPathValue, "/", newXpath);
					identifiedFlag = 1;
					System.out.println(newXpath);
				} else {
					newXpath = tagName + "/" + newXpath;
				}
				break;

			case "Grand_Parent_sibling":
				Grand_Parent_Sibling = Grand_Parents.findElements(By.xpath("preceding-sibling::*[1]"));
				if (Grand_Parent_Sibling.size() > 0) {
					System.out.println("Entering into Grand Parent preceding");
					xPathValue = findElementsTag(Grand_Parent_Sibling);
					System.out.println(xPathValue);
					if (xPathValue != null) {
						newXpath = generateXPathBuilder(xPathValue, "following-sibling::", newXpath);
						identifiedFlag = 1;
						System.out.println(newXpath);
					} else {
						List<WebElement> child = Grand_Parent_Sibling.get(0).findElements(By.xpath("descendant::*"));
						System.out.println(child.size());
						if (child.size() > 0) {
							System.out.println("Entering into Descendant");
							xPathValue = FindChildElementTag(child);
							System.out.println(xPathValue);
							if (xPathValue != null) {
								newXpath = generateXPathBuilder(xPathValue, "/../following-sibling::", newXpath);
								System.out.println(newXpath);
								identifiedFlag = 1;
							}
						}
					}
				}
				break;

			case "Grand_ParentFollow_sibling":
				Grand_Parent_Sibling = Grand_Parents.findElements(By.xpath("following-sibling::*"));
				if (Grand_Parent_Sibling.size() > 0) {
					System.out.println("Entering into Grand Parent preceding");
					xPathValue = findElementsTag(Grand_Parent_Sibling);
					System.out.println(xPathValue);
					if (xPathValue != null) {
						newXpath = generateXPathBuilder(xPathValue, "/preceding-sibling::", newXpath);
						identifiedFlag = 1;
						System.out.println(newXpath);
					}
				}
				break;
			default:
				break;
			}
			iCount++;

		} while (identifiedFlag == 0 & iCount < strFlow.length);
		
*/		if (fieldText != null) {
			if (fieldText.indexOf("\n") > 0) {
				fieldText = fieldText.substring(0, fieldText.indexOf("\n"));
			} else {
				fieldText = fieldText.substring(0, 25);
			}
		}

		return newXpath + "~$" + fieldText;
	}

	public static String FindChildElementTag(List<WebElement> element) {
		String tagName = null;
		System.out.println(element.get(0).getTagName());
		System.out.println(element.get(0).getText());
		for (int j = 0; j < element.size(); j++) {
			System.out.println(element.get(j).getTagName());
			if (element.get(j).getAttribute("for") != null) {
				tagName = "FOR" + "~#" + element.get(j).getTagName() + "~*" + element.get(j).getAttribute("for") + "~$"
						+ element.get(j).getText();
				break;
			} else if (!element.get(j).getText().equals("")) {
				System.out.println(element.get(j).getTagName());
				tagName = "Text-decendent" + "~#" + element.get(j).getTagName() + "~*" + element.get(j).getText() + "~$"
						+ element.get(j).getText();
				break;
			}
		}
		return tagName;
	}

	public static String findElementsTag(List<WebElement> element) {
		String tagName = null;
		System.out.println(element.get(0).getTagName());
		for (int j = 0; j < element.size(); j++) {
			System.out.println(element.get(j).getTagName());
			if (element.get(j).getAttribute("for") != null) {
				tagName = "FOR" + "~#" + element.get(j).getTagName() + "~*" + element.get(j).getAttribute("for") + "~$"
						+ element.get(j).getText();
				break;
			} else if (!element.get(j).getText().equals("")) {
				System.out.println(element.get(j).getTagName());
				List<WebElement> childElements = element.get(j).findElements(By.xpath("descendant::*"));
				if (childElements.size() > 0) {

				} else {
					tagName = "Text-decent" + "~#" + element.get(j).getTagName() + "~*" + element.get(j).getText()
							+ "~$";
				}
				break;
			}
		}
		return tagName;
	}

	public static String findElementsTag(List<WebElement> element, String check) {
		String tagName = null;
		System.out.println(element.get(0).getTagName());
		for (int j = 0; j < element.size(); j++) {
			System.out.println(element.get(j).getTagName());
			if (element.get(j).getAttribute("for") != null) {
				tagName = "FOR" + "~#" + element.get(j).getTagName() + "~*" + element.get(j).getAttribute("for") + "~$"
						+ element.get(j).getText();
				break;
			} else if (!element.get(j).getText().equals("")) {
				System.out.println(element.get(j).getTagName());
				List<WebElement> childElements = element.get(j).findElements(By.xpath("descendant::*"));
				if (childElements.size() > 0) {

				} else {
					tagName = "Text-decent" + "~#" + element.get(j).getTagName() + "~*" + element.get(j).getText()
							+ "~$";
				}
				break;
			}
		}
		return tagName;
	}

	public static String findParentTag(WebElement element, String type) {
		String tagName = null;

		System.out.println(element.getTagName() + " - " + element.getText());
		System.out.println(element.getTagName());
			
		if (element.getAttribute("for") != null) {
			tagName = "FOR" + "~#" + element.getTagName() + "~*" + element.getAttribute("for") + "~$";
			System.out.println(element.getText());
		} else if (!element.getText().equals("") && !(type.equals("select"))) {
			System.out.println(element.findElement(By.xpath("descendant::*")).getTagName());

			List<WebElement> childElements = element.findElements(By.xpath("descendant::*"));
			if (childElements.size() > 0) {
				for (int i = 0; i < childElements.size(); i++) {
					if (childElements.get(i).getText() != null & !(childElements.get(i).getText().equals(""))) {
						tagName = "Text-decent" + "~#" + childElements.get(i).getTagName() + "~*"
								+ childElements.get(i).getText() + "~$";
						break;
					}
				}
			} else {
				tagName = "Text" + "~#" + element.getTagName() + "~*" + element.getText();
			}
		}
		return tagName;
	}

	public static String generateXpath(String attribute, String label, String value) {
		String xpathValue = null;
		value = value.replace("*", "");
		System.out.println("test" + value);
		switch (attribute) {
		case "FOR":
			xpathValue = "//" + label + "[@for='" + value + "']";
			break;
		case "Text":
			if (value.length() > 30) {
				if (value.indexOf("\n") > 0) {
					value = value.substring(0, value.indexOf("\n"));
				} else {
					value = value.substring(0, 30);
				}
				xpathValue = "//" + label + "[contains(text(), '" + value + "')]/..";
			} else {
				xpathValue = "//" + label + "[text()= '" + value + "')]/..";
			}
			break;

		case "Text-decendent":
			if (value.length() > 30) {
				if (value.indexOf("\n") > 0) {
					value = value.substring(0, value.indexOf("\n"));
				} else {
					value = value.substring(0, 30);
				}
				xpathValue = "//" + label + "[contains(text(), '" + value + "')]/";
			} else {
				xpathValue = "//" + label + "[text(), '" + value + "')]/";
			}
			break;
		default:
			break;
		}
		return xpathValue;
	}

	public static String getValue(String value, String valueType) {
		String tempValue = null;
		switch (valueType) {
		case "Attribute":
			tempValue = value.substring(0, value.indexOf("~#"));
			break;

		case "Label":
			tempValue = value.substring(value.indexOf("~#") + 2, value.indexOf("~*"));
			break;

		case "Value":
			tempValue = value.substring(value.indexOf("~*") + 2, value.indexOf("~$"));
			break;

		case "Field Text":
			tempValue = value.substring(value.indexOf("~$") + 2);

		default:
			break;
		}
		return tempValue;
	}

}

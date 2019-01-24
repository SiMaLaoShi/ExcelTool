package com.lary.tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropConf
{

	public static String Key_LastPath = "lastPath"; // 上次打开的路径
	public static String Key_ClientSavePath = "Client"; // 客户端保存路径
	public static String Key_ServerSavePath = "Server"; // 服务器保存路径

	private static Properties prop = new Properties();
	private static File file = new File("./config.properties");

	public static String get(String key)
	{
		try
		{
			if (!file.exists())
			{
				file.createNewFile();
			}
			prop.load(new FileInputStream(file));
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return prop.getProperty(key);
	}

	public static void save(String key, String value)
	{
		prop.setProperty(key, value);
		try
		{
			prop.store(new FileOutputStream(file), key);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}

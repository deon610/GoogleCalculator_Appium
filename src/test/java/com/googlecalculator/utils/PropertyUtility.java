package com.googlecalculator.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * A Singleton class that allows users to use the properties files
 */
public class PropertyUtility
{
	private static PropertyUtility CURR_INSTANCE = null;
	private final Properties props = new Properties();

	private PropertyUtility()
	{
		this.loadProperties("config.properties");
		this.props.putAll(System.getProperties());
	}

	/**
	 * This method is used to return the only instance of the class
	 * @return
	 */
	private static PropertyUtility getInstance()
	{
		if (PropertyUtility.CURR_INSTANCE == null)
		{
			PropertyUtility.CURR_INSTANCE = new PropertyUtility();
		}
		return PropertyUtility.CURR_INSTANCE;
	}

	/**
	 * This method returns the corresponding value from the properties file
	 *
	 * @param	key
	 * @return
	 */
	public static String getProperty(final String propertyKey)
	{
		return PropertyUtility.getInstance().props.getProperty(propertyKey);
	}

	/**
	 * This method fetches the corresponding value from the properties file and
	 * returns an integer value
	 *
	 * @param	key
	 * @param	defaultValue
	 * @return
	 */
	public static int getIntProperty(final String key, final int defaultValue)
	{
		int integerValue = 0;
		final String value = PropertyUtility.getInstance().props.getProperty(key);
		if (value == null)
		{
			return defaultValue;
		}
		integerValue = Integer.parseInt(value);
		return integerValue;
	}

	/**
	 * If the key doesn't exist in the properties file, it returns the default value
	 * passed
	 *
	 * @param	key
	 * @param	defaultValue
	 * @return
	 */
	public static String getProperty(final String key, final String defaultValue)
	{
		return PropertyUtility.getInstance().props.getProperty(key, defaultValue);
	}

	/**
	 * This method will load the properties file
	 *
	 * @param	path
	 */
	private void loadProperties(final String path)
	{
		InputStream inpStream = null;
		try
		{
			inpStream = ClassLoader.getSystemResourceAsStream(path);
			System.out.println(inpStream);
			if (inpStream != null)
			{
				this.props.load(inpStream);
			} else
			{
				throw new FileNotFoundException("Properties file " + path + "' not found in the given classpath");
			}
		} catch (final Exception e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				inpStream.close();
			} catch (final IOException e)
			{
				e.printStackTrace();
			}
		}

		return;
	}

	/**
	 * @return Properties
	 */
	public static Properties getProp()
	{
		return PropertyUtility.getInstance().props;
	}

}

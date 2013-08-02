package com.hyw.sccm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;

/**
 * 长度单位转化和计算工具
 * 
 * @author heyiwang
 * 
 */
public class Length {

	public static void main(String[] args) {
		/**用来存放不同单位对应的转换量*/
		HashMap<String, Float> map = new HashMap<String, Float>();

		try {
			FileReader fr = new FileReader("input.txt");
			BufferedReader br = new BufferedReader(fr);
			String line;
			
			/**读取对应的转换标准，并且保存在HashMap中*/
			while ((line = br.readLine()) != null && !"".equals(line)) {
				String[] array = line.split("=");
				String left = array[0].substring(2).trim();
				float right = Float.valueOf(array[1].substring(0,
						array[1].indexOf("m")).trim());

				map.put(left, right);
			}

			/**开始写文件*/
			BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
			bw.write("heyiwang1234567@126.com\n");

			/**读取要求计算的内容，经过计算之后输出到output.txt中*/
			while ((line = br.readLine()) != null && !"".equals(line)) {
				String[] array = line.split(" ");
				float result = 0;
				int num = (array.length - 2) / 3;// 一共有几个加减符号
				if (num == 0) {
					result = Float.valueOf(array[0]) * map.get(parse(array[1]));
				} else if (num > 0) {
					result = Float.valueOf(array[0]) * map.get(parse(array[1]));
					for (int i = 0; i < num; i++) {
						if ("+".equals(array[3 * (i + 1) - 1])) {
							result += Float.valueOf(array[3 * (i + 1)])
									* map.get(parse(array[3 * (i + 1) + 1]));
						} else if ("-".equals(array[3 * (i + 1) - 1])) {
							result -= Float.valueOf(array[3 * (i + 1)])
									* map.get(parse(array[3 * (i + 1) + 1]));
						}
					}
				}
				/**标准化输出，保留两位小数，並且跟上单位m*/
				DecimalFormat fnum = new DecimalFormat("##0.00");
				String dd = fnum.format(result);
				bw.write("\n" + dd + " m");

			}

			bw.close();
			br.close();
			fr.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	/**
	 * 用来将复数的单位转为单数，以便于在HashMap中找到对应的转换量
	 * @param string
	 * @return
	 */
	private static String parse(String string) {
		if ("miles".equals(string)) {
			return "mile";
		} else if ("yards".equals(string)) {
			return "yard";
		} else if ("inches".equals(string)) {
			return "inch";
		} else if ("feet".equals(string)) {
			return "foot";
		} else if ("faths".equals(string)) {
			return "fath";
		} else if ("furlongs".equals(string)) {
			return "furlong";
		} else {
			return string;
		}
	}
}

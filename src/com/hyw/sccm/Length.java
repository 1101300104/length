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
 * ���ȵ�λת���ͼ��㹤��
 * 
 * @author heyiwang
 * 
 */
public class Length {

	public static void main(String[] args) {
		/**������Ų�ͬ��λ��Ӧ��ת����*/
		HashMap<String, Float> map = new HashMap<String, Float>();

		try {
			FileReader fr = new FileReader("input.txt");
			BufferedReader br = new BufferedReader(fr);
			String line;
			
			/**��ȡ��Ӧ��ת����׼�����ұ�����HashMap��*/
			while ((line = br.readLine()) != null && !"".equals(line)) {
				String[] array = line.split("=");
				String left = array[0].substring(2).trim();
				float right = Float.valueOf(array[1].substring(0,
						array[1].indexOf("m")).trim());

				map.put(left, right);
			}

			/**��ʼд�ļ�*/
			BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
			bw.write("heyiwang1234567@126.com\n");

			/**��ȡҪ���������ݣ���������֮�������output.txt��*/
			while ((line = br.readLine()) != null && !"".equals(line)) {
				String[] array = line.split(" ");
				float result = 0;
				int num = (array.length - 2) / 3;// һ���м����Ӽ�����
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
				/**��׼�������������λС�����K�Ҹ��ϵ�λm*/
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
	 * �����������ĵ�λתΪ�������Ա�����HashMap���ҵ���Ӧ��ת����
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

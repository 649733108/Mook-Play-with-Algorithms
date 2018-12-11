
/*
 * Created by wxn
 * 2018/12/10 19:19
 */


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;

public class ReadGraph {

	Scanner scanner;

	public ReadGraph(Graph graph ,String fileName){
		readFile(fileName);

		int V = scanner.nextInt();
		int E = scanner.nextInt();

		for (int i = 0 ; i<E ; i++){
			int v = scanner.nextInt();
			int w = scanner.nextInt();
			graph.addEdge(v,w);
		}
	}

	private void readFile(String fileName) {
		File file = new File("Graph/"+fileName);
		if (file.exists()){
			try {
				FileInputStream fis = new FileInputStream(file);
				scanner = new Scanner(new BufferedInputStream(fis),"UTF-8");
				scanner.useLocale(Locale.ENGLISH);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}else {
			throw new IllegalArgumentException("Could not open " + fileName);
		}
	}
}

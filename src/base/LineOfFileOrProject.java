package base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

/**
 * 统计文件或项目的行数
 * @version 1.0
 * @author xiehai
 * @date 2014年4月9日 上午11:12:11 
 */
public class LineOfFileOrProject {
	/**空字符串*/
	public static final String NULL = "";
	/**注释开始*/
	public static final String COMMENT_START = "/*";
	/**注释结束*/
	public static final String COMMENT_END = "*/";
	/**单句注释*/
	public static final String LINE_COMMENT = "//";
	/**java文件后缀*/
	public static final String JAVA_FILE = ".java";
	/**普通行*/
	private long normalLines = 0;
	/**空白行*/
	private long blankLines = 0;
	/**注释行*/
	private long commentLines = 0;
	
	/**
	 * 统计文件行数
	 * @param file
	 */
	private void parse(File file){
		BufferedReader br = null;
		boolean isCommentPart = false;
		try {
			br = new BufferedReader(new FileReader(file));
			String line = null;
			while((line = br.readLine()) != null){
//				System.out.println(line);
				line = line.trim();
				if (isCommentPart) {
					commentLines ++;
					if(line.endsWith(COMMENT_END)){
						isCommentPart = false;
					}
				}else if (line.startsWith(COMMENT_START)) {
					if(line.endsWith(COMMENT_END)){
						commentLines ++;
					}else {
						commentLines ++;
						isCommentPart = true;
					}
				}else if (line.startsWith(LINE_COMMENT)) {
					commentLines ++;
				}else if(NULL.equals(line)){
					blankLines ++;
				}else {
					normalLines ++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(null != br){
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 统计路径下文件行数
	 * @param path
	 */
	private void parsePath(String path){
		File file = new File(path);
		if(file.getName().endsWith(JAVA_FILE)){
			parse(file);
			return;
		}
		File []files = file.listFiles();
		Objects.requireNonNull(files);
		for(int i = 0; i < files.length; ++i){
			if(files[i].isDirectory()){
				parsePath(files[i].getPath());
			}else if(files[i].getName().endsWith(JAVA_FILE)){
				parse(files[i]);
			}
		}
	}
	
	/**
	 * 初始化行数
	 */
	private void initCount() {
		this.normalLines = 0;
		this.commentLines = 0;
		this.blankLines = 0;
	}
	
	public void getProjectLinesCount(String path) {
		parsePath(path);
		System.out.println("项目<"+ path +">行数统计："	);
		System.out.println("普通行数：" + normalLines);
		System.out.println("空白行数：" + blankLines);
		System.out.println("注释行数：" + commentLines);
		initCount();
	}
	
	public void getFileLinesCount(String path){
		File file = new File(path);
		parse(file);
		System.out.println(file.getName() + "行数统计："	);
		System.out.println("普通行数：" + normalLines);
		System.out.println("空白行数：" + blankLines);
		System.out.println("注释行数：" + commentLines);
		initCount();
	}
	
	public static void main(String[] args) {
		String path = System.getProperty("user.dir") + "//src";
		LineOfFileOrProject lofop = new LineOfFileOrProject();
//		lofop.getProjectLinesCount(path);
		lofop.getFileLinesCount(path + "//base//LineOfFileOrProject.java");
	}

	/**
	 * @return the normalLines
	 */
	public long getNormalLines() {
		return normalLines;
	}

	/**
	 * @return the blankLines
	 */
	public long getBlankLines() {
		return blankLines;
	}

	/**
	 * @return the commentLines
	 */
	public long getCommentLines() {
		return commentLines;
	}
}

package gui;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class AikenFormatTxt {
	public static AikenFormatTxt getInstance() {
		return new AikenFormatTxt();
	}

	/**
	 * Kiểm tra dòng văn bản thuộc loại gì
	 * 
	 * @param choiceLine Dòng văn bản cần kiểm tra
	 * @return 0 nếu là đáp án câu hỏi, 1 nếu là tiêu đề câu hỏi, -1 nếu là dòng
	 *         trống, 2 nếu là dòng ANSWER
	 */
	public int checkLine(String choiceLine) {
		if (choiceLine.isEmpty()) {
			return -1;
		} else if (Character.isUpperCase(choiceLine.charAt(0)) && choiceLine.charAt(1) == '.'
				&& choiceLine.charAt(2) == ' ') {
			return 0;
		} else if (choiceLine.length() == 9 && choiceLine.substring(0, 8).equals("ANSWER: ")) {
			return 2;
		} else {
			return 1;
		}
	}

	/**
	 * Kiểm tra file .txt có đúng định dạng Aiken format không 
	 * @param path Đường dẫn tới file .txt cần kiểm tra định dạng
	 */
	public void checkFormat(String path) {
		File file = new File(path);

		try {
			int numberQuestions = 0;
			int countChoice = -2;
			int result = 1;

			List<String> allLine = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
			ArrayList<Character> keyChoice = new ArrayList<Character>();

			int numberLine = allLine.size();
			while (true) {
				for (int i = 0; i < numberLine; i++) {
					String checkLine = allLine.get(i);
					if (AikenFormatTxt.getInstance().checkLine(checkLine) == 1 && countChoice == -2) {
						countChoice = 0;
						keyChoice.clear();
						continue;
					} else if (AikenFormatTxt.getInstance().checkLine(checkLine) == 0 && countChoice >= 0) {
						countChoice++;
						keyChoice.add(checkLine.charAt(0));
						continue;
					} else if (AikenFormatTxt.getInstance().checkLine(checkLine) == 2 && countChoice >= 2) {
						if (keyChoice.contains(checkLine.charAt(8))) {
							countChoice = -1;
							numberQuestions++;
							
							continue;
						} else {
							System.out.println("Error at " + (i + 1));
							result = 0;
							countChoice = -2;
						}
					} else if (AikenFormatTxt.getInstance().checkLine(checkLine) == -1 && countChoice == -1) {
						countChoice = -2;
						continue;
					} else {
						System.out.println("Error at " + (i + 1));
						result = 0;
						countChoice = -2;
					}
				}
				break;
			}
			if (result == 1) {
				System.out.println("Success: " + numberQuestions);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

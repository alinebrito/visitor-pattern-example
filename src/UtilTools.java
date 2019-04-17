import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

public class UtilTools {
	
	
	public static String readFileToString(String filePath) throws IOException {
		StringBuilder fileData = new StringBuilder(1000);
		BufferedReader reader = new BufferedReader(new FileReader(filePath));

		char[] buf = new char[10];
		int numRead = 0;
		while ((numRead = reader.read(buf)) != -1) {
			String readData = String.valueOf(buf, 0, numRead);
			fileData.append(readData);
			buf = new char[1024];
		}

		reader.close();

		return fileData.toString();
	}
	
	public static Boolean isJavaFile(final String nameFile){
		return (nameFile!=null && nameFile.endsWith(".java"))?true:false;
	}
	
	public static String getVisibility(BodyDeclaration node){
		for (Object modifier : node.modifiers()) {
			if(modifier.toString().equals("public") || modifier.toString().equals("private")
					|| modifier.toString().equals("protected")){
				return modifier.toString();
			}
		}
		
		return "default";
	}
	
	public static String getTypeName(TypeDeclaration type){
		return type.resolveBinding() == null ? "" : type.resolveBinding().getQualifiedName();
	}
	
	public static boolean isVisibilityPrivate(BodyDeclaration node){
		return getVisibility(node).equals("private");
	}
	
	public static boolean isVisibilityPublic(BodyDeclaration node){
		return getVisibility(node).equals("public");
	}
	
	public static boolean isVisibilityDefault(BodyDeclaration node){
		return getVisibility(node).equals("default");
	}
	
	public static boolean isVisibilityProtected(BodyDeclaration node){
		return getVisibility(node).equals("protected");
	}

}

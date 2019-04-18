import java.io.IOException;

import org.eclipse.jdt.core.dom.TypeDeclaration;

public class Main {
	
	public static void main(String[] args) throws IOException {
		
		Parser parser = new Parser();
		parser.visitAllJavaFiles("/home/aline/utilitarios/workspaces/ws_ufmg/projetos/visitor-pattern-example");
		
		System.out.println("Public classes: ");
		for(TypeDeclaration type: parser.getTypes()){
			if(UtilTools.isVisibilityPublic(type)){
				System.out.println(UtilTools.getTypeName(type));
			}
		}

	}

}

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.TypeDeclaration;

public class Parser {
	
	private List<TypeDeclaration> types = new ArrayList<TypeDeclaration>();
	
	public List<TypeDeclaration> getTypes() {
		return types;
	}

	public void visitAllJavaFiles(final String path) throws IOException {
		File project = new File(path);
		this.findJavaFiles(project);
	}
	
	private void findJavaFiles(File file) throws IOException {
		if (UtilTools.isJavaFile(file)) {
			this.visitJavaFile(file);		
		} else {
			if(file.listFiles() != null){
				for (File f : file.listFiles()) {
					this.findJavaFiles(f);
				}
			}
		}
	}
	
	private void visitJavaFile(File javaFile) throws IOException {
		
		ASTParser parser = ASTParser.newParser(AST.JLS8);
		String source = UtilTools.readFileToString(javaFile.getAbsolutePath());
		String[] classpath = java.lang.System.getProperty("java.class.path").split(";");
		String[] sources = { javaFile.getParentFile().getAbsolutePath() };
		
		Hashtable<String, String> options = JavaCore.getOptions();
		options.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_1_8);
		options.put(JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM, JavaCore.VERSION_1_8);
		options.put(JavaCore.COMPILER_COMPLIANCE, JavaCore.VERSION_1_8);
		
		parser.setSource(source.toCharArray());
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setUnitName(javaFile.getAbsolutePath());
		parser.setCompilerOptions(options);
		parser.setResolveBindings(true);
		parser.setBindingsRecovery(true);
		parser.setEnvironment(null, null, null,	true);
		
		CompilationUnit compilationUnit = (CompilationUnit) parser.createAST(null);
		
		TypeVisitor visitorType = new TypeVisitor();
		compilationUnit.accept(visitorType);
		
		this.types.addAll(visitorType.getTypes());
	}

}

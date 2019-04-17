import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

public class Parser {
	
	private String path;
	
	private String nameProject;
	
	public Parser(final String path, final String nameProject) {
		this.path = path;
		this.nameProject = nameProject;
	}
	
	public void parseFilesInDir(File file) throws IOException {
	}

	
	public void process(String str, File source) {
		
		ASTParser parser = ASTParser.newParser(AST.JLS8);
		parser.setSource(str.toCharArray());
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		String[] classpath = java.lang.System.getProperty("java.class.path").split(";");
		String[] sources = { source.getParentFile().getAbsolutePath() };
		Hashtable<String, String> options = JavaCore.getOptions();
		options.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_1_8);
		options.put(JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM, JavaCore.VERSION_1_8);
		options.put(JavaCore.COMPILER_COMPLIANCE, JavaCore.VERSION_1_8);
		parser.setUnitName(source.getAbsolutePath());
		parser.setCompilerOptions(options);
		parser.setResolveBindings(true);
		parser.setBindingsRecovery(true);

		parser.setEnvironment(null, null, null,	true);
		CompilationUnit compilationUnit = (CompilationUnit) parser.createAST(null);
		
		//TODO: Incluir novos visitors.
		TypeVisitor visitorType = new TypeVisitor();
		compilationUnit.accept(visitorType);
	}

}

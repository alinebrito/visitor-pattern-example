

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.TypeDeclaration;

public class TypeVisitor extends ASTVisitor{
	
	/**
	 * Imprime o nome da classe se ela for pública.
	 * @param typeVisitor
	 */
	public void printNameTypes(TypeDeclaration type) {
		if(UtilTools.isVisibilityPublic(type)){
			System.out.println(UtilTools.getTypeName(type));
		}
	}

	@Override
	public boolean visit(TypeDeclaration type) {
		return super.visit(type);
	}


}

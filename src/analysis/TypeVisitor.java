package analysis;


import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.TypeDeclaration;

public class TypeVisitor extends ASTVisitor{
	
	private List<TypeDeclaration> types = new ArrayList<TypeDeclaration>();
	
	public List<TypeDeclaration> getTypes() {
		return types;
	}

	public void setTypes(List<TypeDeclaration> types) {
		this.types = types;
	}

	@Override
	public boolean visit(TypeDeclaration type) {
		this.types.add(type);
		return super.visit(type);
	}

}

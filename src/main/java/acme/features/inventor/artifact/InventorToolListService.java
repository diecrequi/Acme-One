package acme.features.inventor.artifact;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import acme.artifact.Artifact;
import acme.artifact.ArtifactType;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

public class InventorToolListService implements AbstractListService<Inventor, Artifact>{

	@Autowired
	protected InventorArtifactRepository repository;
	
	
	@Override
	public boolean authorise(final Request<Artifact> request) {
		assert request != null;

		return true;
	}

	@Override
	public Collection<Artifact> findMany(final Request<Artifact> request) {
		assert request != null;
		
		Collection<Artifact> result;
		
		result= this.repository.findManyArtifact(ArtifactType.TOOL);
		
		return null;
	}

	@Override
	public void unbind(final Request<Artifact> request, final Artifact entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "code", "description");
		
	}

	
}

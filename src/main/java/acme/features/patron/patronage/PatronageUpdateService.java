package acme.features.patron.patronage;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import acme.entities.patronage.Patronage;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.HttpMethod;
import acme.framework.controllers.Request;
import acme.framework.controllers.Response;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Patron;

@Service
public class PatronageUpdateService implements AbstractUpdateService<Patron, Patronage>{
	
	@Autowired
	protected PatronageRepository repository;

	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "id", "status", "code", "legalStuff", "budget", "startDate", "link");
		model.setAttribute("inventorId", entity.getInventor().getUserAccount().getId());
		model.setAttribute("esInventor", false);
	}

	@Override
	public Patronage findOne(final Request<Patronage> request) {
		assert request != null;

		Patronage result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOnePatronageById(id);

		return result;
	}

	@Override
	public void bind(Request<Patronage> request, Patronage entity, Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "id", "status", "code", "legalStuff", "budget", "startDate", "link");
		
	}

	@Override
	public void validate(Request<Patronage> request, Patronage entity, Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	}

	@Override
	public void update(Request<Patronage> request, Patronage entity) {
		assert request !=null;
		assert entity !=null;
		
		this.repository.save(entity);
		
	}
	
	@Override
	public void onSuccess(final Request<Patronage> request, final Response<Patronage> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}

}
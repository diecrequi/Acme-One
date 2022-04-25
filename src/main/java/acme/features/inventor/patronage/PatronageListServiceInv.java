package acme.features.inventor.patronage;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronage.Patronage;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service
public class PatronageListServiceInv implements AbstractListService<Inventor, Patronage>{
	
	// Internal state ---------------------------------------------------------

		@Autowired
		protected PatronageRepositoryInv repository;

		// AbstractListService<Anonymous, Announcement>  interface -------------------------


		@Override
		public boolean authorise(final Request<Patronage> request) {
			assert request != null;

			return true;
		}
		
		@Override
		public Collection<Patronage> findMany(final Request<Patronage> request) {
			assert request != null;

			Collection<Patronage> result;

			result = this.repository.findManyPatronage();

			return result;
		}
		
		@Override
		public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;

			request.unbind(entity, model, "code", "budget");
		}

}

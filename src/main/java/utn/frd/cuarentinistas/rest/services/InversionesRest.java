package utn.frd.cuarentinistas.rest.services;

import java.util.List;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import utn.frd.cuarentinistas.entities.Inversiones;
import utn.frd.cuarentinistas.entities.Movimientos;
import utn.frd.cuarentinistas.sessions.InversionesFacade;


@Path("/inversiones")
public class InversionesRest {

    EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "my_persistence_unit" );
    EntityManager em = emfactory.createEntityManager( );
    @EJB
    private InversionesFacade ejbInversionesFacade;

    //obtener todas las entidades
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Inversiones> findAll(){
        return ejbInversionesFacade.findAll();
    }

    //crear entidades
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Inversiones inversion){
        ejbInversionesFacade.create(inversion);
    }

    //actualizar entidades
    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/{id}")
    public void edit(@PathParam("id")long id, Inversiones inversion){
        ejbInversionesFacade.edit(inversion);
    }

    //eliminar entidades
    @DELETE
    @Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    @Path("/{id}")
    public void remove(@PathParam("id")long id){
        ejbInversionesFacade.remove( ejbInversionesFacade.find(id) );
    }

    @GET
    @Path("/cuenta/{inversionistaCbu}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Inversiones> findByInversionistaCbu(@PathParam("inversionistaCbu")int cbu){
        TypedQuery<Inversiones> q = em.createNamedQuery("Inversiones.findByInversionistaCbu", Inversiones.class);
        q.setParameter("inversionistaCbu", cbu);
        return q.getResultList();
    }
}
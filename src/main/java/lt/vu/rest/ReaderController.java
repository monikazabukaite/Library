package lt.vu.rest;

import lombok.Getter;
import lombok.Setter;
import lt.vu.dao.ReadersDAO;
import lt.vu.entities.Reader;
import lt.vu.rest.dto.ReaderDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@Getter @Setter
@Path("/readers")
public class ReaderController {

    @Inject
    private ReadersDAO readersDAO;

    @Path("/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<ReaderDTO> readers = readersDAO.loadAll().stream().map(ReaderDTO::new).collect(Collectors.toList());

        return Response.ok(readers).build();
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Integer id) {
        ReaderDTO reader = new ReaderDTO(readersDAO.findOne(id));

        return Response.ok(reader).build();
    }

    @Path("/{id}")
    @PUT @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(@PathParam("id") final Integer id, ReaderDTO reader) {

        try {
            Reader existingReader = readersDAO.findOne(id);
            if (existingReader == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            existingReader.setName(reader.getName());
            readersDAO.update(existingReader);
            return Response.ok().build();
        } catch (OptimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @Path("/new")
    @POST @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response write(ReaderDTO reader) {
        try {
            if (reader.getName() == null || reader.getName().isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
            Reader newReader = new Reader();
            newReader.setName(reader.getName());
            newReader.setBooks(new HashSet<>());
            readersDAO.persist(newReader);
            return Response.ok().build();
        } catch (OptimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }



}

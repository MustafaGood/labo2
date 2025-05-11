package com.mustafa.warehouseapi1.resource;

import com.mustafa.warehouseapi1.model.Product;
import com.mustafa.warehouseapi1.service.WarehouseService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import java.util.List;
import java.util.logging.Logger;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {
    private static final Logger logger = Logger.getLogger(ProductResource.class.getName());

    @Inject
    private WarehouseService warehouseService;

    @Context
    private UriInfo uriInfo;

    @GET
    public Response getAllProducts(
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("10") int size) {
        logger.info("Getting all products with pagination: page=" + page + ", size=" + size);
        List<Product> products = warehouseService.getAllProducts();
        return Response.ok(products).build();
    }

    @GET
    @Path("/{id}")
    public Response getProduct(@PathParam("id") Long id) {
        logger.info("Getting product with id: " + id);
        Product product = warehouseService.getProduct(id);
        if (product == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Product not found with id: " + id)
                    .build();
        }
        return Response.ok(product).build();
    }

    @GET
    @Path("/category/{category}")
    public Response getProductsByCategory(@PathParam("category") String category) {
        logger.info("Getting products by category: " + category);
        List<Product> products = warehouseService.getProductsByCategory(category);
        return Response.ok(products).build();
    }

    @POST
    @Transactional
    public Response createProduct(@Valid Product product) {
        logger.info("Creating new product: " + product.getName());
        try {
            warehouseService.addProduct(product);
            return Response.status(Response.Status.CREATED)
                    .entity(product)
                    .build();
        } catch (Exception e) {
            logger.severe("Error creating product: " + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Error creating product: " + e.getMessage())
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateProduct(@PathParam("id") Long id, @Valid Product product) {
        logger.info("Updating product with id: " + id);
        try {
            warehouseService.updateProduct(id, product);
            return Response.ok(product).build();
        } catch (Exception e) {
            logger.severe("Error updating product: " + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Error updating product: " + e.getMessage())
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteProduct(@PathParam("id") Long id) {
        logger.info("Deleting product with id: " + id);
        try {
            warehouseService.deleteProduct(id);
            return Response.noContent().build();
        } catch (Exception e) {
            logger.severe("Error deleting product: " + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Error deleting product: " + e.getMessage())
                    .build();
        }
    }
} 
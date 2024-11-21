package gr.athtech.work1.resource;

import gr.athtech.work1.model.Product;
import jakarta.ws.rs.*;

import java.util.ArrayList;
import java.util.List;

@Path("/products")
public class HelloResource {

    private static final List<Product> products = new ArrayList<>();

    //CRUD
    //Post, Get, Put, Delete


    @GET
    @Path("/ping/")
    @Consumes("application/json")
    @Produces("application/json")
    public Product hello() {
        return new Product();
    }


    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Product createProduct(Product product) {
        products.add(product);
        return product;
    }

    @GET
    @Path("/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Product getProduct(@PathParam("id") int id) {
        return  products.get(id);
    }

    @GET
    @Consumes("application/json")
    @Produces("application/json")
    public List<Product> getProduct( ) {
        return  products;
    }

@PUT
@Path("/{id}")
@Consumes("application/json")
@Produces("application/json")
public List<Product> editProduct(@PathParam("id") int id, Product newDataProduct ) {
        Product oldDataProduct = products.get(id);
        oldDataProduct.setName(newDataProduct.getName());
        oldDataProduct.setPrice(newDataProduct.getPrice());
    return  products;
}

    @DELETE
    @Path("/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public boolean deleteProduct(@PathParam("id") int id) {
        Product oldDataProduct = products.get(id);
        if (oldDataProduct == null) { return false; }
        products.remove(oldDataProduct);
        return  true;
    }



}
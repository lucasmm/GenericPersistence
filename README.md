GenericPersistence
==================

Cria um Dao Genérico, para que o objeto possa ser persistido na Entidade.

##Exemplo de Uso

```java
public class Artist extends GenericDao<Artist> implements Serializable {

    private Long id;
    private String artistName;
}

public class Main {

    public static void main(String[] args) {

        try {

            Artist artist = new Artist();
            artist.setArtistName("Artist");
            artist.save();

    }
}
```

Para o projeto completo, acesse o projeto https://github.com/heitormaffra/CrudGP

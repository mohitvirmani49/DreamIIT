package Model;

public class Upload {
    private String image_name;
    private String imageUri;


    public Upload() {

    }


    public Upload(String image_name, String imageUri) {
        if(image_name.trim().equals("")){
            image_name = "No Name";
        }
        this.image_name = image_name;
        this.imageUri = imageUri;
//        this.username = username;

    }

    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

}

@Service
public class ImageService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void uploadImage(Image image) {
        // code to upload the image to Imgur
        // publish a message to Kafka with the username and image name
        String message = String.format("User '%s' uploaded image '%s'", image.getUser().getUsername(), image.getName());
        kafkaTemplate.send("image-uploads", message);
    }
}

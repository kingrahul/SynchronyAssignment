@Service
public class ImgurService {

    private final ImgurClient client = new ImgurClient("your-client-id-here", "your-client-secret-here");
    private final ImgurApi api = new ImgurApi(client);

    public Image uploadImage(MultipartFile file, String clientId) {
        try {
            InputStream inputStream = file.getInputStream();
            ImgurImage uploadedImage = api.upload(inputStream, null);
            return new Image(uploadedImage.getId(), uploadedImage.getTitle(), uploadedImage.getLink());
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload image", e);
        }
    }

    public Resource getImage(Long id, String clientId) {
        ImgurImage image = api.getImage(id.toString());
        InputStream inputStream = new ByteArrayInputStream(image.getBytes());
        return new InputStreamResource(inputStream);
    }

    public void deleteImage(Long id, String clientId) {
        api.deleteImage(id.toString());
    }
}

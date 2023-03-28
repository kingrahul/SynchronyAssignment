@RestController
@RequestMapping("/api/images")
public class ImageController {

    private static final String IMGUR_CLIENT_ID = "your-client-id-here";

    @Autowired
    private ImgurService imgurService;

    @PostMapping("/upload")
    public ResponseEntity<Image> uploadImage(@RequestParam("file") MultipartFile file) {
        Image uploadedImage = imgurService.uploadImage(file, IMGUR_CLIENT_ID);
        return ResponseEntity.created(URI.create("/api/images/" + uploadedImage.getId())).body(uploadedImage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getImage(@PathVariable Long id) {
        Resource image = imgurService.getImage(id, IMGUR_CLIENT_ID);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable Long id) {
        imgurService.deleteImage(id, IMGUR_CLIENT_ID);
        return ResponseEntity.noContent().build();
    }
}

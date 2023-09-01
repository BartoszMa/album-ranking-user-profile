package com.albumranking.albumrankinguserprofile.controllers;

import com.albumranking.albumrankinguserprofile.models.Profile;
import com.albumranking.albumrankinguserprofile.service.ProfileService;
import org.apache.hc.core5.http.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.User;
import se.michaelthelin.spotify.requests.data.users_profile.GetCurrentUsersProfileRequest;

@RestController
@RequestMapping(path = "/")
@CrossOrigin(origins = "${API_GATEWAY}")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }


    @GetMapping("profile-info/{token}")
    @ResponseBody
    public ResponseEntity<String> getProfileInfo(@PathVariable("token") String token) throws IOException, ParseException, SpotifyWebApiException {

        SpotifyApi spotifyApi = new SpotifyApi.Builder()
                .setAccessToken(token)
                .build();

        GetCurrentUsersProfileRequest getCurrentUsersProfileRequest = spotifyApi.getCurrentUsersProfile()
                .build();

        final User user = getCurrentUsersProfileRequest.execute();
        System.out.println("Display name: " + user.getDisplayName());

        if (!profileService.checkIfProfileExist(user.getId())) {
            profileService.createProfile(new Profile(user.getId(), user.getEmail(), user.getDisplayName(), Arrays.toString(user.getImages())));
        }

        return ResponseEntity.ok(profileService.getProfile(user.getId()).toString());
    }

//        URL obj = new URL(SPOTIFY_PROFILE_URL);
//        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//
//        con.setRequestMethod("GET");
//        con.setRequestProperty("Content-Type","application/json");
//        con.setRequestProperty("Authorization", "Bearer " + token);
//
//        int responseCode = con.getResponseCode();
//
//        if (responseCode == HttpURLConnection.HTTP_OK) {
//            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
//            String output;
//            StringBuilder response = new StringBuilder();
//            while ((output = in.readLine()) != null) {
//                response.append(output);
//            }
//
//            in.close();
//
//
//            return ResponseEntity.ok(response.toString());
//        }
//
//        return ResponseEntity.status(400).body("Error connection");
//    }

}

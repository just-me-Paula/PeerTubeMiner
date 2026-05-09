package com.Peertube.Miner.controller;

import com.Peertube.Miner.etl.Transformer;
import com.Peertube.Miner.model.peertube.Channel;
import com.Peertube.Miner.model.videominer.VMChannel;
import com.Peertube.Miner.service.ChannelService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/peertubeminer")
public class ChannelController {

    private final ChannelService channelService;
    private final Transformer transformer;
    private final RestTemplate restTemplate;

    @Value("${videominer.baseUri}")
    private String videominerBaseUri;

    public ChannelController(ChannelService channelService,
                             Transformer transformer,
                             RestTemplate restTemplate) {
        this.channelService = channelService;
        this.transformer = transformer;
        this.restTemplate = restTemplate;
    }

    /**
     * GET /peertubeminer/{id} - Preview channel data without sending to VideoMiner.
     */
    @GetMapping("/{id}")
    public VMChannel previewChannel(
            @PathVariable String id,
            @RequestParam(defaultValue = "10") int maxVideos,
            @RequestParam(defaultValue = "2") int maxComments) {

        return buildVMChannel(id, maxVideos, maxComments);
    }

    /**
     * POST /peertubeminer/{id} - Fetch channel from PeerTube and send it to VideoMiner.
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{id}")
    public VMChannel sendToVideoMiner(
            @PathVariable String id,
            @RequestParam(defaultValue = "10") int maxVideos,
            @RequestParam(defaultValue = "2") int maxComments) {

        VMChannel vmChannel = buildVMChannel(id, maxVideos, maxComments);

        String url = videominerBaseUri + "/channels";
        HttpEntity<VMChannel> request = new HttpEntity<>(vmChannel);

        try {
            ResponseEntity<VMChannel> response =
                    restTemplate.exchange(url, HttpMethod.POST, request, VMChannel.class);
            return response.getBody();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY,
                    "Error sending channel to VideoMiner: " + e.getMessage(), e);
        }
    }

    private VMChannel buildVMChannel(String channelHandle, int maxVideos, int maxComments) {
        Channel channel = channelService.fetchChannelById(channelHandle);
        if (channel == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Channel not found in PeerTube: " + channelHandle);
        }
        return transformer.toVMChannel(channel, channelHandle, maxVideos, maxComments);
    }
}

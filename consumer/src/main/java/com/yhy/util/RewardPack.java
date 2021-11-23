package com.yhy.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RewardPack {
    private List<Integer> uids;
    private int exp;
    private int coins;
    private int diamonds;
    private String entrance_effect_expiry;
    private String entrance_effect;
    private String avatar_frame_expiry;
    private String avatar_frame;
    private String chat_bubble_expiry;
    private String chat_bubble;
    private String cover_frame_expiry;
    private String cover_frame;
}

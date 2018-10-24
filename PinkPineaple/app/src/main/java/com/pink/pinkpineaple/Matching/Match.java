package com.pink.pinkpineaple.Matching;

import com.pink.pinkpineaple.User;

public class Match {

    //An instance of match will be created once two users 'Match'
    // A match will hold the messages,likes and what not...........

    private User person1;
    private User person2;

    public Match(User person1, User person2){
        this.person1 = person1;
        this.person2 = person2;
    }
}

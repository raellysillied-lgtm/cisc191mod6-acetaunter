package edu.sdccd.cisc191;

import java.util.*;

public class GameServerAnalytics {

    public static List<String> findTopNUsernamesByRating(Collection<PlayerAccount> players, int n) { // Correct standard approach
        // TODO: use a stream pipeline
        return players.stream()
                .sorted(Comparator.comparingInt(PlayerAccount::rating).reversed())
                .limit(n)
                .map(PlayerAccount::username)
                .toList();
    }

    public static Map<String, Double> averageRatingByRegion(Collection<PlayerAccount> players) {
        // TODO: use groupingBy + averagingInt
        return players.stream()
                .collect(java.util.stream.Collectors.groupingBy(
                        PlayerAccount::region,
                        Collectors.averagingInt(PlayerAccount::rating)
                ));
    }

    public static Set<String> findDuplicateUsernames(Collection<PlayerAccount> players) {
        // TODO: use collections and/or streams
        // Again, really like this return logic, though I think it makes more sense to implement the use of streams instead
        return counts.entrySet().stream()  // Map.Entry<String, Long>
                .filter(entry -> entry.getValue() > 1) // check which has more than one
                .map(entry -> entry.getKey()) // throw away values
                .collect(Collectors.toSet());
    }

    public static Map<String, List<String>> groupUsernamesByTier(Collection<PlayerAccount> players) { // Completely correct, just cleaned up syntax
        // TODO: use groupingBy and mapping
        return players.stream()
                .sorted(Comparator.comparing(PlayerAccount::username)) // sort alphabetically
                .collect(Collectors.groupingBy(
                        GameServerAnalytics::tierFor, // groups of tiers
                        Collectors.mapping(PlayerAccount::username, Collectors.toList()) // accumulate into names
                ));
    }

    public static Map<String, List<String>> buildRecentMatchSummariesByPlayer(Collection<MatchRecord> matches) {
        // TODO: use a Map + collection logic or a stream-based approach
        Map<String, List<String>> out = new HashMap<>();
        for (var m : matches) {
            out.computeIfAbsent(m.playerOne().username(), k -> new ArrayList<>()).add(m.summary());
            out.computeIfAbsent(m.playerTwo().username(), k -> new ArrayList<>()).add(m.summary());
        }
        return out;
    }

    public static <T> T pickHigherRated(T first, T second, Comparator<T> comparator) {
        // TODO: implement using the comparator
        return comparator.compare(first, second) >= 0 ? first : second;
    }

    public static String tierFor(PlayerAccount player) {
        if (player.rating() < 1000) return "Bronze";
        if (player.rating() < 1400) return "Silver";
        return "Gold";
    }
}

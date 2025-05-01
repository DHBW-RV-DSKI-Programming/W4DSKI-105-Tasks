record Version (String versionText) implements Comparable<Version> {

    @Override
    public int compareTo(Version other) {
        String[] parts = versionText.split("\\.");
        String[] otherParts = other.versionText.split("\\.");
        for (int i = 0; i < Math.min(parts.length, otherParts.length); i++) {
            int part = Integer.parseInt(parts[i]);
            int otherPart = Integer.parseInt(otherParts[i]);
            if (part != otherPart) {
                return part - otherPart;
            }
        }
        return parts.length - otherParts.length;
    }

}

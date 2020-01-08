package snake;

import java.io.Serializable;

public class Scores implements Serializable
{
    private Integer slug;
    private Integer worm;
    private Integer python;
    private Integer blackMamba;
    public Scores()
    {
        slug=0;
        worm=0;
        python=0;
        blackMamba=0;
    }

    public Integer getSlug() {
        return slug;
    }

    public void setSlug(Integer slug) {
        this.slug = slug;
    }

    public Integer getWorm() {
        return worm;
    }

    public void setWorm(Integer worm) {
        this.worm = worm;
    }

    public Integer getPython() {
        return python;
    }

    public void setPython(Integer python) {
        this.python = python;
    }

    public Integer getBlackMamba() {
        return blackMamba;
    }

    public void setBlackMamba(Integer blackMamba) {
        this.blackMamba = blackMamba;
    }
}

(ns audio-player.core
  (:require [clojure.java.io :as io])
  (:import javax.sound.sampled.AudioSystem
           javax.sound.sampled.DataLine$Info
           javax.sound.sampled.Clip
           javax.sound.sampled.LineUnavailableException)
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let [mixer-infos (AudioSystem/getMixerInfo)
        mixer (AudioSystem/getMixer (get mixer-infos 1))
        data-info (DataLine$Info. Clip nil)
        clip (try (.getLine mixer data-info) (catch LineUnavailableException lue (println "Line unavailable for clip")))
        sound-url (try (io/resource "sound2.wav") (catch Exception e (println "Resource loading failed for sound-url")))
        audio-stream (try (AudioSystem/getAudioInputStream sound-url) (catch Exception e (println "Get audio input stream failed")))]
    (.open clip audio-stream)
    (.start clip)
    (Thread/sleep 5000)
    ))

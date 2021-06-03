/*
 * ADOBE CONFIDENTIAL
 *
 * Copyright 2017 Adobe Systems Incorporated
 * All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains
 * the property of Adobe Systems Incorporated and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * herein are proprietary to Adobe Systems Incorporated and its
 * suppliers and may be covered by U.S. and Foreign Patents,
 * patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Adobe Systems Incorporated.
 */
;(function(document, $) {
    var MutationObserver = window.MutationObserver || window.WebKitMutationObserver || window.MozMutationObserver,
        mutationConfig = { attributes: true },
        observer = new MutationObserver(function(mutations) {
            mutations.forEach(function () {
                bindPlugin();
            });
        });

    $(function() {
        bindPlugin();
        observer.observe(document.documentElement, mutationConfig);
    });

    function bindPlugin() {
        $("div[id^='cq-image-jsp-']").each(function(i, el) {
            $('img[usemap]', el).rwdImageMaps();
        });
    }
})
(document, $CQ);
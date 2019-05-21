/**
 * @license
 * Copyright (c) 2018 amCharts (Antanas Marcelionis, Martynas Majeris)
 *
 * This sofware is provided under multiple licenses. Please see below for
 * links to appropriate usage.
 *
 * Free amCharts linkware license. Details and conditions:
 * https://github.com/amcharts/amcharts4/blob/master/LICENSE
 *
 * One of the amCharts commercial licenses. Details and pricing:
 * https://www.amcharts.com/online-store/
 * https://www.amcharts.com/online-store/licenses-explained/
 *
 * If in doubt, contact amCharts at contact@amcharts.com
 *
 * PLEASE DO NOT REMOVE THIS COPYRIGHT NOTICE.
 * @hidden
 */
am4internal_webpackJsonp(["3741"], {
    "eUZ+": function (e, t, n) {
        "use strict";
        Object.defineProperty(t, "__esModule", {value: !0});
        var i = {};
        n.d(i, "ForceDirectedLink", function () {
            return c
        }), n.d(i, "ForceDirectedTreeDataItem", function () {
            return xe
        }), n.d(i, "ForceDirectedTree", function () {
            return be
        }), n.d(i, "ForceDirectedNode", function () {
            return g
        }), n.d(i, "ForceDirectedSeriesDataItem", function () {
            return ge
        }), n.d(i, "ForceDirectedSeries", function () {
            return me
        });
        var r = n("m4/l"), o = n("Vs7R"), a = n("aCit"), s = n("MIZb"), l = n("hGwe"), c = function (e) {
            function t() {
                var t = e.call(this) || this;
                t.className = "ForceDirectedLink";
                var n = new s.a;
                return t.fillOpacity = 0, t.strokeOpacity = .5, t.stroke = n.getFor("grid"), t.isMeasured = !1, t.nonScalingStroke = !0, t.interactionsEnabled = !1, t.distance = 1.5, t.strength = 1, t.applyTheme(), t
            }

            return r.c(t, e), t.prototype.validate = function () {
                e.prototype.validate.call(this);
                var t = this.source, n = this.target;
                t && n && (this.path = l.moveTo({x: t.pixelX, y: t.pixelY}) + l.lineTo({
                    x: n.pixelX,
                    y: n.pixelY
                }), t.isHidden || n.isHidden || t.isHiding || n.isHiding ? this.hide() : this.show())
            }, Object.defineProperty(t.prototype, "source", {
                get: function () {
                    return this._source
                }, set: function (e) {
                    e && (this._source = e, this._disposers.push(e.events.on("positionchanged", this.invalidate, this, !1)))
                }, enumerable: !0, configurable: !0
            }), Object.defineProperty(t.prototype, "target", {
                get: function () {
                    return this._target
                }, set: function (e) {
                    e && (this._target = e, this._disposers.push(e.events.on("positionchanged", this.invalidate, this, !1)))
                }, enumerable: !0, configurable: !0
            }), Object.defineProperty(t.prototype, "distance", {
                get: function () {
                    return this.adapter.isEnabled("distance") ? this.adapter.apply("distance", this.properties.distance) : this.properties.distance
                }, set: function (e) {
                    this.setPropertyValue("distance", e)
                }, enumerable: !0, configurable: !0
            }), Object.defineProperty(t.prototype, "strength", {
                get: function () {
                    return this.adapter.isEnabled("strength") ? this.adapter.apply("strength", this.properties.strength) : this.properties.strength
                }, set: function (e) {
                    this.setPropertyValue("strength", e)
                }, enumerable: !0, configurable: !0
            }), t
        }(o.a);
        a.b.registeredClasses.ForceDirectedLink = c;
        var u = n("2I/e"), h = n("aM7D"), d = n("vMqJ"), f = n("C6dT"), p = n("FzPm"), y = n("p9TX"), v = n("Mtpk"),
            g = function (e) {
                function t() {
                    var t = e.call(this) || this;
                    t.className = "ForceDirectedNode", t.applyOnClones = !0, t.togglable = !0, t.draggable = !0, t.setStateOnChildren = !0, t.isActive = !1, t.events.on("dragstart", function () {
                        t.dataItem.component && t.dataItem.component.nodeDragStarted()
                    }, t, !1), t.events.on("drag", function () {
                        t.updateSimulation()
                    }, t, !1);
                    var n = t.createChild(p.a);
                    n.shouldClone = !1, n.strokeWidth = 2;
                    var i = (new s.a).getFor("background");
                    n.fill = i, t.outerCircle = n, n.states.create("hover").properties.scale = 1.1;
                    var r = n.states.create("active");
                    r.properties.scale = 1.1, r.properties.visible = !0, n.states.create("hoverActive").properties.scale = 1;
                    var o = t.createChild(p.a);
                    o.states.create("active").properties.visible = !0, o.shouldClone = !1, o.interactionsEnabled = !1, o.hiddenState.properties.radius = .01, o.events.on("validated", t.updateSimulation, t, !1), o.hiddenState.properties.visible = !0, t.circle = o, t.addDisposer(n.events.on("validated", t.updateLabelSize, t, !1)), t._disposers.push(t.circle);
                    var a = t.createChild(y.a);
                    return a.shouldClone = !1, a.horizontalCenter = "middle", a.verticalCenter = "middle", a.fill = i, a.strokeOpacity = 0, a.interactionsEnabled = !1, a.textAlign = "middle", a.textValign = "middle", t.label = a, t.adapter.add("tooltipY", function (e, t) {
                        return -t.circle.pixelRadius
                    }), t
                }

                return r.c(t, e), t.prototype.updateLabelSize = function () {
                    if (this.label.text) {
                        var e = this.circle, t = e.pixelRadius, n = e.defaultState.properties.radius;
                        v.isNumber(n) && (t = n), this.label.width = 2 * t, this.label.height = 2 * t
                    }
                }, t.prototype.copyFrom = function (t) {
                    e.prototype.copyFrom.call(this, t), this.circle && this.circle.copyFrom(t.circle), this.label && this.label.copyFrom(t.label), this.outerCircle && this.outerCircle.copyFrom(t.outerCircle)
                }, t.prototype.setActive = function (t) {
                    e.prototype.setActive.call(this, t);
                    var n = this.dataItem;
                    if (n) {
                        var i = n.children;
                        t && i && !n.childrenInited && (n.component.initNode(n), n.component.updateNodeList()), t ? (i && i.each(function (e) {
                            e.node.show(), e.node.interactionsEnabled = !0, e.parentLink && e.parentLink.show(), e.node.isActive = !0
                        }), n.dispatchVisibility(!0)) : (i && i.each(function (e) {
                            e.parentLink && e.parentLink.hide(), e.node.isActive = !1, e.node.interactionsEnabled = !1, e.node.hide()
                        }), n.dispatchVisibility(!1))
                    }
                    this.updateSimulation()
                }, t.prototype.updateSimulation = function () {
                    var e = this.dataItem;
                    e && e.component && e.component.restartSimulation()
                }, t
            }(f.a);
        a.b.registeredClasses.ForceDirectedNode = g;
        var m = n("hD5A"), x = n("DHte");

        function b(e, t, n, i) {
            if (isNaN(t) || isNaN(n)) return e;
            var r, o, a, s, l, c, u, h, d, f = e._root, p = {data: i}, y = e._x0, v = e._y0, g = e._x1, m = e._y1;
            if (!f) return e._root = p, e;
            for (; f.length;) if ((c = t >= (o = (y + g) / 2)) ? y = o : g = o, (u = n >= (a = (v + m) / 2)) ? v = a : m = a, r = f, !(f = f[h = u << 1 | c])) return r[h] = p, e;
            if (s = +e._x.call(null, f.data), l = +e._y.call(null, f.data), t === s && n === l) return p.next = f, r ? r[h] = p : e._root = p, e;
            do {
                r = r ? r[h] = new Array(4) : e._root = new Array(4), (c = t >= (o = (y + g) / 2)) ? y = o : g = o, (u = n >= (a = (v + m) / 2)) ? v = a : m = a
            } while ((h = u << 1 | c) == (d = (l >= a) << 1 | s >= o));
            return r[d] = f, r[h] = p, e
        }

        var _ = function (e, t, n, i, r) {
            this.node = e, this.x0 = t, this.y0 = n, this.x1 = i, this.y1 = r
        };

        function w(e) {
            return e[0]
        }

        function k(e) {
            return e[1]
        }

        function I(e, t, n) {
            var i = new S(null == t ? w : t, null == n ? k : n, NaN, NaN, NaN, NaN);
            return null == e ? i : i.addAll(e)
        }

        function S(e, t, n, i, r, o) {
            this._x = e, this._y = t, this._x0 = n, this._y0 = i, this._x1 = r, this._y1 = o, this._root = void 0
        }

        function N(e) {
            for (var t = {data: e.data}, n = t; e = e.next;) n = n.next = {data: e.data};
            return t
        }

        var L = I.prototype = S.prototype;
        L.copy = function () {
            var e, t, n = new S(this._x, this._y, this._x0, this._y0, this._x1, this._y1), i = this._root;
            if (!i) return n;
            if (!i.length) return n._root = N(i), n;
            for (e = [{
                source: i,
                target: n._root = new Array(4)
            }]; i = e.pop();) for (var r = 0; r < 4; ++r) (t = i.source[r]) && (t.length ? e.push({
                source: t,
                target: i.target[r] = new Array(4)
            }) : i.target[r] = N(t));
            return n
        }, L.add = function (e) {
            var t = +this._x.call(null, e), n = +this._y.call(null, e);
            return b(this.cover(t, n), t, n, e)
        }, L.addAll = function (e) {
            var t, n, i, r, o = e.length, a = new Array(o), s = new Array(o), l = 1 / 0, c = 1 / 0, u = -1 / 0,
                h = -1 / 0;
            for (n = 0; n < o; ++n) isNaN(i = +this._x.call(null, t = e[n])) || isNaN(r = +this._y.call(null, t)) || (a[n] = i, s[n] = r, i < l && (l = i), i > u && (u = i), r < c && (c = r), r > h && (h = r));
            if (l > u || c > h) return this;
            for (this.cover(l, c).cover(u, h), n = 0; n < o; ++n) b(this, a[n], s[n], e[n]);
            return this
        }, L.cover = function (e, t) {
            if (isNaN(e = +e) || isNaN(t = +t)) return this;
            var n = this._x0, i = this._y0, r = this._x1, o = this._y1;
            if (isNaN(n)) r = (n = Math.floor(e)) + 1, o = (i = Math.floor(t)) + 1; else {
                for (var a, s, l = r - n, c = this._root; n > e || e >= r || i > t || t >= o;) switch (s = (t < i) << 1 | e < n, (a = new Array(4))[s] = c, c = a, l *= 2, s) {
                    case 0:
                        r = n + l, o = i + l;
                        break;
                    case 1:
                        n = r - l, o = i + l;
                        break;
                    case 2:
                        r = n + l, i = o - l;
                        break;
                    case 3:
                        n = r - l, i = o - l
                }
                this._root && this._root.length && (this._root = c)
            }
            return this._x0 = n, this._y0 = i, this._x1 = r, this._y1 = o, this
        }, L.data = function () {
            var e = [];
            return this.visit(function (t) {
                if (!t.length) do {
                    e.push(t.data)
                } while (t = t.next)
            }), e
        }, L.extent = function (e) {
            return arguments.length ? this.cover(+e[0][0], +e[0][1]).cover(+e[1][0], +e[1][1]) : isNaN(this._x0) ? void 0 : [[this._x0, this._y0], [this._x1, this._y1]]
        }, L.find = function (e, t, n) {
            var i, r, o, a, s, l, c, u = this._x0, h = this._y0, d = this._x1, f = this._y1, p = [], y = this._root;
            for (y && p.push(new _(y, u, h, d, f)), null == n ? n = 1 / 0 : (u = e - n, h = t - n, d = e + n, f = t + n, n *= n); l = p.pop();) if (!(!(y = l.node) || (r = l.x0) > d || (o = l.y0) > f || (a = l.x1) < u || (s = l.y1) < h)) if (y.length) {
                var v = (r + a) / 2, g = (o + s) / 2;
                p.push(new _(y[3], v, g, a, s), new _(y[2], r, g, v, s), new _(y[1], v, o, a, g), new _(y[0], r, o, v, g)), (c = (t >= g) << 1 | e >= v) && (l = p[p.length - 1], p[p.length - 1] = p[p.length - 1 - c], p[p.length - 1 - c] = l)
            } else {
                var m = e - +this._x.call(null, y.data), x = t - +this._y.call(null, y.data), b = m * m + x * x;
                if (b < n) {
                    var w = Math.sqrt(n = b);
                    u = e - w, h = t - w, d = e + w, f = t + w, i = y.data
                }
            }
            return i
        }, L.remove = function (e) {
            if (isNaN(o = +this._x.call(null, e)) || isNaN(a = +this._y.call(null, e))) return this;
            var t, n, i, r, o, a, s, l, c, u, h, d, f = this._root, p = this._x0, y = this._y0, v = this._x1,
                g = this._y1;
            if (!f) return this;
            if (f.length) for (; ;) {
                if ((c = o >= (s = (p + v) / 2)) ? p = s : v = s, (u = a >= (l = (y + g) / 2)) ? y = l : g = l, t = f, !(f = f[h = u << 1 | c])) return this;
                if (!f.length) break;
                (t[h + 1 & 3] || t[h + 2 & 3] || t[h + 3 & 3]) && (n = t, d = h)
            }
            for (; f.data !== e;) if (i = f, !(f = f.next)) return this;
            return (r = f.next) && delete f.next, i ? (r ? i.next = r : delete i.next, this) : t ? (r ? t[h] = r : delete t[h], (f = t[0] || t[1] || t[2] || t[3]) && f === (t[3] || t[2] || t[1] || t[0]) && !f.length && (n ? n[d] = f : this._root = f), this) : (this._root = r, this)
        }, L.removeAll = function (e) {
            for (var t = 0, n = e.length; t < n; ++t) this.remove(e[t]);
            return this
        }, L.root = function () {
            return this._root
        }, L.size = function () {
            var e = 0;
            return this.visit(function (t) {
                if (!t.length) do {
                    ++e
                } while (t = t.next)
            }), e
        }, L.visit = function (e) {
            var t, n, i, r, o, a, s = [], l = this._root;
            for (l && s.push(new _(l, this._x0, this._y0, this._x1, this._y1)); t = s.pop();) if (!e(l = t.node, i = t.x0, r = t.y0, o = t.x1, a = t.y1) && l.length) {
                var c = (i + o) / 2, u = (r + a) / 2;
                (n = l[3]) && s.push(new _(n, c, u, o, a)), (n = l[2]) && s.push(new _(n, i, u, c, a)), (n = l[1]) && s.push(new _(n, c, r, o, u)), (n = l[0]) && s.push(new _(n, i, r, c, u))
            }
            return this
        }, L.visitAfter = function (e) {
            var t, n = [], i = [];
            for (this._root && n.push(new _(this._root, this._x0, this._y0, this._x1, this._y1)); t = n.pop();) {
                var r = t.node;
                if (r.length) {
                    var o, a = t.x0, s = t.y0, l = t.x1, c = t.y1, u = (a + l) / 2, h = (s + c) / 2;
                    (o = r[0]) && n.push(new _(o, a, s, u, h)), (o = r[1]) && n.push(new _(o, u, s, l, h)), (o = r[2]) && n.push(new _(o, a, h, u, c)), (o = r[3]) && n.push(new _(o, u, h, l, c))
                }
                i.push(t)
            }
            for (; t = i.pop();) e(t.node, t.x0, t.y0, t.x1, t.y1);
            return this
        }, L.x = function (e) {
            return arguments.length ? (this._x = e, this) : this._x
        }, L.y = function (e) {
            return arguments.length ? (this._y = e, this) : this._y
        };
        var O = function (e) {
            return function () {
                return e
            }
        }, D = function () {
            return 1e-6 * (Math.random() - .5)
        };

        function P(e) {
            return e.x + e.vx
        }

        function A(e) {
            return e.y + e.vy
        }

        function F(e) {
            return e.index
        }

        function M(e, t) {
            var n = e.get(t);
            if (!n) throw new Error("missing: " + t);
            return n
        }

        var T = {
            value: function () {
            }
        };

        function C() {
            for (var e, t = 0, n = arguments.length, i = {}; t < n; ++t) {
                if (!(e = arguments[t] + "") || e in i) throw new Error("illegal type: " + e);
                i[e] = []
            }
            return new j(i)
        }

        function j(e) {
            this._ = e
        }

        function V(e, t) {
            for (var n, i = 0, r = e.length; i < r; ++i) if ((n = e[i]).name === t) return n.value
        }

        function R(e, t, n) {
            for (var i = 0, r = e.length; i < r; ++i) if (e[i].name === t) {
                e[i] = T, e = e.slice(0, i).concat(e.slice(i + 1));
                break
            }
            return null != n && e.push({name: t, value: n}), e
        }

        j.prototype = C.prototype = {
            constructor: j, on: function (e, t) {
                var n, i = this._, r = function (e, t) {
                    return e.trim().split(/^|\s+/).map(function (e) {
                        var n = "", i = e.indexOf(".");
                        if (i >= 0 && (n = e.slice(i + 1), e = e.slice(0, i)), e && !t.hasOwnProperty(e)) throw new Error("unknown type: " + e);
                        return {type: e, name: n}
                    })
                }(e + "", i), o = -1, a = r.length;
                if (!(arguments.length < 2)) {
                    if (null != t && "function" != typeof t) throw new Error("invalid callback: " + t);
                    for (; ++o < a;) if (n = (e = r[o]).type) i[n] = R(i[n], e.name, t); else if (null == t) for (n in i) i[n] = R(i[n], e.name, null);
                    return this
                }
                for (; ++o < a;) if ((n = (e = r[o]).type) && (n = V(i[n], e.name))) return n
            }, copy: function () {
                var e = {}, t = this._;
                for (var n in t) e[n] = t[n].slice();
                return new j(e)
            }, call: function (e, t) {
                if ((n = arguments.length - 2) > 0) for (var n, i, r = new Array(n), o = 0; o < n; ++o) r[o] = arguments[o + 2];
                if (!this._.hasOwnProperty(e)) throw new Error("unknown type: " + e);
                for (o = 0, n = (i = this._[e]).length; o < n; ++o) i[o].value.apply(t, r)
            }, apply: function (e, t, n) {
                if (!this._.hasOwnProperty(e)) throw new Error("unknown type: " + e);
                for (var i = this._[e], r = 0, o = i.length; r < o; ++r) i[r].value.apply(t, n)
            }
        };
        var E, W, z = C, q = 0, H = 0, B = 0, X = 1e3, Y = 0, J = 0, U = 0,
            Z = "object" == typeof performance && performance.now ? performance : Date,
            G = "object" == typeof window && window.requestAnimationFrame ? window.requestAnimationFrame.bind(window) : function (e) {
                setTimeout(e, 17)
            };

        function K() {
            return J || (G(Q), J = Z.now() + U)
        }

        function Q() {
            J = 0
        }

        function $() {
            this._call = this._time = this._next = null
        }

        function ee(e, t, n) {
            var i = new $;
            return i.restart(e, t, n), i
        }

        function te() {
            J = (Y = Z.now()) + U, q = H = 0;
            try {
                !function () {
                    K(), ++q;
                    for (var e, t = E; t;) (e = J - t._time) >= 0 && t._call.call(null, e), t = t._next;
                    --q
                }()
            } finally {
                q = 0, function () {
                    var e, t, n = E, i = 1 / 0;
                    for (; n;) n._call ? (i > n._time && (i = n._time), e = n, n = n._next) : (t = n._next, n._next = null, n = e ? e._next = t : E = t);
                    W = e, ie(i)
                }(), J = 0
            }
        }

        function ne() {
            var e = Z.now(), t = e - Y;
            t > X && (U -= t, Y = e)
        }

        function ie(e) {
            q || (H && (H = clearTimeout(H)), e - J > 24 ? (e < 1 / 0 && (H = setTimeout(te, e - Z.now() - U)), B && (B = clearInterval(B))) : (B || (Y = Z.now(), B = setInterval(ne, X)), q = 1, G(te)))
        }

        $.prototype = ee.prototype = {
            constructor: $, restart: function (e, t, n) {
                if ("function" != typeof e) throw new TypeError("callback is not a function");
                n = (null == n ? K() : +n) + (null == t ? 0 : +t), this._next || W === this || (W ? W._next = this : E = this, W = this), this._call = e, this._time = n, ie()
            }, stop: function () {
                this._call && (this._call = null, this._time = 1 / 0, ie())
            }
        };

        function re(e) {
            return e.x
        }

        function oe(e) {
            return e.y
        }

        var ae = 10, se = Math.PI * (3 - Math.sqrt(5)), le = function (e) {
                var t, n = 1, i = .001, r = 1 - Math.pow(i, 1 / 300), o = 0, a = .6, s = new Map, l = ee(u),
                    c = z("tick", "end");

                function u() {
                    h(), c.call("tick", t), n < i && (l.stop(), c.call("end", t))
                }

                function h(i) {
                    var l, c, u = e.length;
                    void 0 === i && (i = 1);
                    for (var h = 0; h < i; ++h) for (n += (o - n) * r, s.forEach(function (e) {
                        e(n)
                    }), l = 0; l < u; ++l) null == (c = e[l]).fx ? c.x += c.vx *= a : (c.x = c.fx, c.vx = 0), null == c.fy ? c.y += c.vy *= a : (c.y = c.fy, c.vy = 0);
                    return t
                }

                function d() {
                    for (var t, n = 0, i = e.length; n < i; ++n) {
                        if ((t = e[n]).index = n, null != t.fx && (t.x = t.fx), null != t.fy && (t.y = t.fy), isNaN(t.x) || isNaN(t.y)) {
                            var r = ae * Math.sqrt(n), o = n * se;
                            t.x = r * Math.cos(o), t.y = r * Math.sin(o)
                        }
                        (isNaN(t.vx) || isNaN(t.vy)) && (t.vx = t.vy = 0)
                    }
                }

                function f(t) {
                    return t.initialize && t.initialize(e), t
                }

                return null == e && (e = []), d(), t = {
                    tick: h, restart: function () {
                        return l.restart(u), t
                    }, stop: function () {
                        return l.stop(), t
                    }, nodes: function (n) {
                        return arguments.length ? (e = n, d(), s.forEach(f), t) : e
                    }, alpha: function (e) {
                        return arguments.length ? (n = +e, t) : n
                    }, alphaMin: function (e) {
                        return arguments.length ? (i = +e, t) : i
                    }, alphaDecay: function (e) {
                        return arguments.length ? (r = +e, t) : +r
                    }, alphaTarget: function (e) {
                        return arguments.length ? (o = +e, t) : o
                    }, velocityDecay: function (e) {
                        return arguments.length ? (a = 1 - e, t) : 1 - a
                    }, force: function (e, n) {
                        return arguments.length > 1 ? (null == n ? s.delete(e) : s.set(e, f(n)), t) : s.get(e)
                    }, find: function (t, n, i) {
                        var r, o, a, s, l, c = 0, u = e.length;
                        for (null == i ? i = 1 / 0 : i *= i, c = 0; c < u; ++c) (a = (r = t - (s = e[c]).x) * r + (o = n - s.y) * o) < i && (l = s, i = a);
                        return l
                    }, on: function (e, n) {
                        return arguments.length > 1 ? (c.on(e, n), t) : c.on(e)
                    }
                }
            }, ce = function (e) {
                var t, n, i, r = O(.1);

                function o(e) {
                    for (var r, o = 0, a = t.length; o < a; ++o) (r = t[o]).vx += (i[o] - r.x) * n[o] * e
                }

                function a() {
                    if (t) {
                        var o, a = t.length;
                        for (n = new Array(a), i = new Array(a), o = 0; o < a; ++o) n[o] = isNaN(i[o] = +e(t[o], o, t)) ? 0 : +r(t[o], o, t)
                    }
                }

                return "function" != typeof e && (e = O(null == e ? 0 : +e)), o.initialize = function (e) {
                    t = e, a()
                }, o.strength = function (e) {
                    return arguments.length ? (r = "function" == typeof e ? e : O(+e), a(), o) : r
                }, o.x = function (t) {
                    return arguments.length ? (e = "function" == typeof t ? t : O(+t), a(), o) : e
                }, o
            }, ue = function (e) {
                var t, n, i, r = O(.1);

                function o(e) {
                    for (var r, o = 0, a = t.length; o < a; ++o) (r = t[o]).vy += (i[o] - r.y) * n[o] * e
                }

                function a() {
                    if (t) {
                        var o, a = t.length;
                        for (n = new Array(a), i = new Array(a), o = 0; o < a; ++o) n[o] = isNaN(i[o] = +e(t[o], o, t)) ? 0 : +r(t[o], o, t)
                    }
                }

                return "function" != typeof e && (e = O(null == e ? 0 : +e)), o.initialize = function (e) {
                    t = e, a()
                }, o.strength = function (e) {
                    return arguments.length ? (r = "function" == typeof e ? e : O(+e), a(), o) : r
                }, o.y = function (t) {
                    return arguments.length ? (e = "function" == typeof t ? t : O(+t), a(), o) : e
                }, o
            }, he = n("Gg2j"), de = n("v9UT"), fe = n("hJ5i"), pe = n("tjMS"), ye = n("qCRI"), ve = n("CnhP"),
            ge = function (e) {
                function t() {
                    var t = e.call(this) || this;
                    return t.childrenInited = !1, t.className = "ForceDirectedSeriesDataItem", t.hasChildren.children = !0, t.childLinks = new d.b, t.applyTheme(), t
                }

                return r.c(t, e), t.prototype.show = function (e, t, n) {
                    this._visible = !0, this.node && (this.node.isActive = !0)
                }, t.prototype.dispatchVisibility = function (e) {
                    if (this.events.isEnabled("visibilitychanged")) {
                        var t = {type: "visibilitychanged", target: this, visible: e};
                        this.events.dispatchImmediately("visibilitychanged", t)
                    }
                }, t.prototype.hide = function (e, t, n, i) {
                    if (this._visible = !1, this.events.isEnabled("visibilitychanged")) {
                        var r = {type: "visibilitychanged", target: this, visible: !1};
                        this.events.dispatchImmediately("visibilitychanged", r)
                    }
                    this.node && (this.node.isActive = !1)
                }, Object.defineProperty(t.prototype, "value", {
                    get: function () {
                        var e = this.values.value.value;
                        return v.isNumber(e) || this.children && (e = 0, this.children.each(function (t) {
                            e += t.value
                        })), e
                    }, set: function (e) {
                        this.setValue("value", e)
                    }, enumerable: !0, configurable: !0
                }), Object.defineProperty(t.prototype, "node", {
                    get: function () {
                        var e = this;
                        if (!this._node) {
                            var t = this.component, n = t.nodes.create();
                            n.draggable = !0, this._node = n, this._disposers.push(n), this._disposers.push(new m.b(function () {
                                t.nodes.removeValue(n)
                            })), this.addSprite(n), n.visible = this.visible, n.hiddenState.properties.visible = !0, t.itemsFocusable() ? (n.role = "menuitem", n.focusable = !0) : (n.role = "listitem", n.focusable = !1), n.focusable && (n.events.once("focus", function (i) {
                                n.readerTitle = t.populateString(t.itemReaderText, e)
                            }, void 0, !1), n.events.once("blur", function (e) {
                                n.readerTitle = ""
                            }, void 0, !1)), n.hoverable && (n.events.once("over", function (i) {
                                n.readerTitle = t.populateString(t.itemReaderText, e)
                            }, void 0, !1), n.events.once("out", function (e) {
                                n.readerTitle = ""
                            }, void 0, !1))
                        }
                        return this._node
                    }, enumerable: !0, configurable: !0
                }), Object.defineProperty(t.prototype, "level", {
                    get: function () {
                        return this.parent ? this.parent.level + 1 : 0
                    }, enumerable: !0, configurable: !0
                }), Object.defineProperty(t.prototype, "color", {
                    get: function () {
                        var e = this.properties.color;
                        return void 0 == e && this.parent && (console.log("parent"), e = this.parent.color), void 0 == e && this.component && (e = this.component.colors.getIndex(this.component.colors.step * this.index)), e
                    }, set: function (e) {
                        this.setProperty("color", e)
                    }, enumerable: !0, configurable: !0
                }), Object.defineProperty(t.prototype, "linkWith", {
                    get: function () {
                        return this.properties.linkWith
                    }, set: function (e) {
                        this.setProperty("linkWith", e)
                    }, enumerable: !0, configurable: !0
                }), Object.defineProperty(t.prototype, "hiddenInLegend", {
                    get: function () {
                        return this.properties.hiddenInLegend
                    }, set: function (e) {
                        this.setProperty("hiddenInLegend", e)
                    }, enumerable: !0, configurable: !0
                }), Object.defineProperty(t.prototype, "collapsed", {
                    get: function () {
                        return this.properties.collapsed
                    }, set: function (e) {
                        this.setProperty("collapsed", e), this.node.isActive = !e
                    }, enumerable: !0, configurable: !0
                }), Object.defineProperty(t.prototype, "children", {
                    get: function () {
                        return this.properties.children
                    }, set: function (e) {
                        this.setProperty("children", e)
                    }, enumerable: !0, configurable: !0
                }), t.prototype.createLegendMarker = function (e) {
                    this.component.createLegendMarker(e, this), this.node.isActive || this.hide()
                }, Object.defineProperty(t.prototype, "legendDataItem", {
                    get: function () {
                        return this._legendDataItem
                    }, set: function (e) {
                        this._legendDataItem = e, e.label && (e.label.dataItem = this), e.valueLabel && (e.valueLabel.dataItem = this)
                    }, enumerable: !0, configurable: !0
                }), t
            }(h.b), me = function (e) {
                function t() {
                    var t = e.call(this) || this;
                    return t.className = "ForceDirectedSeries", t.d3forceSimulation = le(), t.maxRadius = Object(pe.c)(8), t.minRadius = Object(pe.c)(1), t.width = Object(pe.c)(100), t.height = Object(pe.c)(100), t.colors = new x.a, t.colors.step = 2, t.width = Object(pe.c)(100), t.height = Object(pe.c)(100), t.manyBodyStrength = -15, t.centerStrength = .8, t.events.on("maxsizechanged", function () {
                        t.updateRadiuses(t.dataItems), t.updateLinksAndNodes();
                        var e = t.d3forceSimulation;
                        e && (e.force("x", ce().x(t.innerWidth / 2).strength(100 * t.centerStrength / t.innerWidth)), e.force("y", ue().y(t.innerHeight / 2).strength(100 * t.centerStrength / t.innerHeight)), e.alpha() < .4 && (e.alpha(.4), e.restart()))
                    }), t.applyTheme(), t
                }

                return r.c(t, e), t.prototype.getMaxValue = function (e, t) {
                    var n = this;
                    return e.each(function (e) {
                        if (e.value > t && (t = e.value), e.children) {
                            var i = n.getMaxValue(e.children, t);
                            i > t && (t = i)
                        }
                    }), t
                }, t.prototype.validateDataItems = function () {
                    var t = this;
                    this._maxValue = this.getMaxValue(this.dataItems, 0), this._forceLinks = [], this.colors.reset();
                    var n = 0, i = Math.min(this.innerHeight / 3, this.innerWidth / 3);
                    this.dataItems.length <= 1 && (i = 0), this.dataItems.each(function (e) {
                        var r = n / t.dataItems.length * 360;
                        e.node.x = t.innerWidth / 2 + i * he.cos(r), e.node.y = t.innerHeight / 2 + i * he.sin(r), e.node.fill = e.color, e.node.stroke = e.color, n++, t.initNode(e)
                    }), this.dataFields.linkWith && this.dataItems.each(function (e) {
                        t.processLinkWith(e)
                    });
                    var r = this.d3forceSimulation;
                    r.alphaDecay(1 - Math.pow(.001, 1 / 600)), r.on("tick", function () {
                        t.updateLinksAndNodes()
                    }), this.chart.feedLegend(), e.prototype.validateDataItems.call(this)
                }, t.prototype.updateNodeList = function () {
                    var e = this.d3forceSimulation;
                    e.nodes(this.nodes.values), this._linkForce = function (e) {
                        var t, n, i, r, o, a = F, s = function (e) {
                            return 1 / Math.min(r[e.source.index], r[e.target.index])
                        }, l = O(30), c = 1;

                        function u(i) {
                            for (var r = 0, a = e.length; r < c; ++r) for (var s, l, u, h, d, f, p, y = 0; y < a; ++y) l = (s = e[y]).source, h = (u = s.target).x + u.vx - l.x - l.vx || D(), d = u.y + u.vy - l.y - l.vy || D(), h *= f = ((f = Math.sqrt(h * h + d * d)) - n[y]) / f * i * t[y], d *= f, u.vx -= h * (p = o[y]), u.vy -= d * p, l.vx += h * (p = 1 - p), l.vy += d * p
                        }

                        function h() {
                            if (i) {
                                var s, l, c = i.length, u = e.length, h = new Map(i.map((e, t) => [a(e, t, i), e]));
                                for (s = 0, r = new Array(c); s < u; ++s) (l = e[s]).index = s, "object" != typeof l.source && (l.source = M(h, l.source)), "object" != typeof l.target && (l.target = M(h, l.target)), r[l.source.index] = (r[l.source.index] || 0) + 1, r[l.target.index] = (r[l.target.index] || 0) + 1;
                                for (s = 0, o = new Array(u); s < u; ++s) l = e[s], o[s] = r[l.source.index] / (r[l.source.index] + r[l.target.index]);
                                t = new Array(u), d(), n = new Array(u), f()
                            }
                        }

                        function d() {
                            if (i) for (var n = 0, r = e.length; n < r; ++n) t[n] = +s(e[n], n, e)
                        }

                        function f() {
                            if (i) for (var t = 0, r = e.length; t < r; ++t) n[t] = +l(e[t], t, e)
                        }

                        return null == e && (e = []), u.initialize = function (e) {
                            i = e, h()
                        }, u.links = function (t) {
                            return arguments.length ? (e = t, h(), u) : e
                        }, u.id = function (e) {
                            return arguments.length ? (a = e, u) : a
                        }, u.iterations = function (e) {
                            return arguments.length ? (c = +e, u) : c
                        }, u.strength = function (e) {
                            return arguments.length ? (s = "function" == typeof e ? e : O(+e), d(), u) : s
                        }, u.distance = function (e) {
                            return arguments.length ? (l = "function" == typeof e ? e : O(+e), f(), u) : l
                        }, u
                    }(this._forceLinks), e.force("link", this._linkForce), this._collisionForce = function (e) {
                        var t, n, i = 1, r = 1;

                        function o() {
                            for (var e, o, s, l, c, u, h, d = t.length, f = 0; f < r; ++f) for (o = I(t, P, A).visitAfter(a), e = 0; e < d; ++e) s = t[e], u = n[s.index], h = u * u, l = s.x + s.vx, c = s.y + s.vy, o.visit(p);

                            function p(e, t, n, r, o) {
                                var a = e.data, d = e.r, f = u + d;
                                if (!a) return t > l + f || r < l - f || n > c + f || o < c - f;
                                if (a.index > s.index) {
                                    var p = l - a.x - a.vx, y = c - a.y - a.vy, v = p * p + y * y;
                                    v < f * f && (0 === p && (v += (p = D()) * p), 0 === y && (v += (y = D()) * y), v = (f - (v = Math.sqrt(v))) / v * i, s.vx += (p *= v) * (f = (d *= d) / (h + d)), s.vy += (y *= v) * f, a.vx -= p * (f = 1 - f), a.vy -= y * f)
                                }
                            }
                        }

                        function a(e) {
                            if (e.data) return e.r = n[e.data.index];
                            for (var t = e.r = 0; t < 4; ++t) e[t] && e[t].r > e.r && (e.r = e[t].r)
                        }

                        function s() {
                            if (t) {
                                var i, r, o = t.length;
                                for (n = new Array(o), i = 0; i < o; ++i) r = t[i], n[r.index] = +e(r, i, t)
                            }
                        }

                        return "function" != typeof e && (e = O(null == e ? 1 : +e)), o.initialize = function (e) {
                            t = e, s()
                        }, o.iterations = function (e) {
                            return arguments.length ? (r = +e, o) : r
                        }, o.strength = function (e) {
                            return arguments.length ? (i = +e, o) : i
                        }, o.radius = function (t) {
                            return arguments.length ? (e = "function" == typeof t ? t : O(+t), s(), o) : e
                        }, o
                    }(), e.force("collision", this._collisionForce), e.force("x", ce().x(this.innerWidth / 2).strength(100 * this.centerStrength / this.innerWidth)), e.force("y", ue().y(this.innerHeight / 2).strength(100 * this.centerStrength / this.innerHeight))
                }, t.prototype.updateLinksAndNodes = function () {
                    var e = this;
                    this._linkForce && (this._linkForce.distance(function (t) {
                        return e.getDistance(t)
                    }), this._linkForce.strength(function (t) {
                        return e.getStrength(t)
                    })), this._collisionForce && this._collisionForce.radius(function (e) {
                        if (e instanceof g) {
                            var t = e.circle.pixelRadius;
                            return e.outerCircle.__disabled || e.outerCircle.disabled || !e.outerCircle.visible || (t = (t + 3) * e.outerCircle.scale), t
                        }
                        return 1
                    }), this.d3forceSimulation.force("manybody", function () {
                        var e, t, n, i, r = O(-30), o = 1, a = 1 / 0, s = .81;

                        function l(i) {
                            var r, o = e.length, a = I(e, re, oe).visitAfter(u);
                            for (n = i, r = 0; r < o; ++r) t = e[r], a.visit(h)
                        }

                        function c() {
                            if (e) {
                                var t, n, o = e.length;
                                for (i = new Array(o), t = 0; t < o; ++t) n = e[t], i[n.index] = +r(n, t, e)
                            }
                        }

                        function u(e) {
                            var t, n, r, o, a, s = 0, l = 0;
                            if (e.length) {
                                for (r = o = a = 0; a < 4; ++a) (t = e[a]) && (n = Math.abs(t.value)) && (s += t.value, l += n, r += n * t.x, o += n * t.y);
                                e.x = r / l, e.y = o / l
                            } else {
                                (t = e).x = t.data.x, t.y = t.data.y;
                                do {
                                    s += i[t.data.index]
                                } while (t = t.next)
                            }
                            e.value = s
                        }

                        function h(e, r, l, c) {
                            if (!e.value) return !0;
                            var u = e.x - t.x, h = e.y - t.y, d = c - r, f = u * u + h * h;
                            if (d * d / s < f) return f < a && (0 === u && (f += (u = D()) * u), 0 === h && (f += (h = D()) * h), f < o && (f = Math.sqrt(o * f)), t.vx += u * e.value * n / f, t.vy += h * e.value * n / f), !0;
                            if (!(e.length || f >= a)) {
                                (e.data !== t || e.next) && (0 === u && (f += (u = D()) * u), 0 === h && (f += (h = D()) * h), f < o && (f = Math.sqrt(o * f)));
                                do {
                                    e.data !== t && (d = i[e.data.index] * n / f, t.vx += u * d, t.vy += h * d)
                                } while (e = e.next)
                            }
                        }

                        return l.initialize = function (t) {
                            e = t, c()
                        }, l.strength = function (e) {
                            return arguments.length ? (r = "function" == typeof e ? e : O(+e), c(), l) : r
                        }, l.distanceMin = function (e) {
                            return arguments.length ? (o = e * e, l) : Math.sqrt(o)
                        }, l.distanceMax = function (e) {
                            return arguments.length ? (a = e * e, l) : Math.sqrt(a)
                        }, l.theta = function (e) {
                            return arguments.length ? (s = e * e, l) : Math.sqrt(s)
                        }, l
                    }().strength(function (t) {
                        return t instanceof g ? t.circle.pixelRadius * e.manyBodyStrength : e.manyBodyStrength
                    }))
                }, t.prototype.getDistance = function (e) {
                    var t = e.source, n = e.target, i = 0;
                    return n.dataItem && t.dataItem ? (n.dataItem.parentLink && (i = n.dataItem.parentLink.distance), t.isActive || (i = 1), i * (t.circle.pixelRadius + n.circle.pixelRadius)) : i
                }, t.prototype.getStrength = function (e) {
                    var t = e.target, n = 0;
                    return t.dataItem && t.dataItem.parentLink && (n = t.dataItem.parentLink.strength), n
                }, t.prototype.nodeDragEnded = function () {
                    this.d3forceSimulation.alphaTarget(0)
                }, t.prototype.nodeDragStarted = function () {
                    this.d3forceSimulation.alpha(.1), this.d3forceSimulation.restart()
                }, t.prototype.restartSimulation = function () {
                    this.d3forceSimulation.alpha() <= .3 && (this.d3forceSimulation.alpha(.3), this.d3forceSimulation.restart())
                }, t.prototype.updateRadiuses = function (e) {
                    var t = this;
                    e.each(function (e) {
                        t.updateRadius(e), e.childrenInited && t.updateRadiuses(e.children)
                    })
                }, t.prototype.updateRadius = function (e) {
                    var t = e.node, n = (this.innerWidth + this.innerHeight) / 2, i = de.relativeToValue(this.minRadius, n),
                        r = de.relativeToValue(this.maxRadius, n), o = i + e.value / this._maxValue * (r - i);
                    v.isNumber(o) || (o = i), t.circle.isHidden || (t.circle.radius = o), t.outerCircle.radius = o + 3, t.circle.states.getKey("active").properties.radius = o, t.circle.defaultState.properties.radius = o
                }, t.prototype.initNode = function (e) {
                    var t = this, n = e.node;
                    n.parent = this, this.updateRadius(e);
                    var i = this.nodes.indexOf(e.node);
                    if (e.children && 0 != e.children.length ? n.cursorOverStyle = ye.a.pointer : (n.outerCircle.__disabled = !0, n.circle.interactionsEnabled = !0, n.cursorOverStyle = ye.a.default), this.dataItemsInvalid && (e.level >= this.maxLevels - 1 || e.collapsed)) return n.isActive = !1, void this.updateNodeList();
                    if (e.children) {
                        var r = 0;
                        e.childrenInited = !0, 1 == this.dataItems.length && 0 == e.level && this.colors.next(), e.children.each(function (o) {
                            var a = t.links.create();
                            a.parent = t, a.zIndex = -1, e.childLinks.push(a), a.source = e.node;
                            var s = t.nodes.indexOf(o.node);
                            a.target = o.node, o.parentLink = a, t._forceLinks.push({source: i, target: s});
                            var l, c = 2 * n.circle.pixelRadius + o.node.circle.pixelRadius,
                                u = r / e.children.length * 360;
                            o.node.x = n.pixelX + c * he.cos(u), o.node.y = n.pixelY + c * he.sin(u), o.node.circle.radius = 0;
                            var h = o.properties.color;
                            l = v.hasValue(h) ? h : 1 == t.dataItems.length && 0 == e.level ? t.colors.next() : e.color, o.color = l, o.node.fill = l, o.node.stroke = l, o.parentLink.stroke = l, t.initNode(o), r++
                        })
                    }
                    n.isActive = !0, this.updateNodeList()
                }, t.prototype.processLinkWith = function (e) {
                    var t = this;
                    e.linkWith && fe.each(e.linkWith, function (n, i) {
                        var r = t.getDataItemById(t.dataItems, n);
                        if (r) {
                            var o = t.links.create();
                            o.parent = t, o.zIndex = -1, o.source = e.node, o.target = r.node, o.stroke = e.node.fill, e.childLinks.push(o)
                        }
                    }), e.children && e.children.each(function (e) {
                        t.processLinkWith(e)
                    })
                }, t.prototype.getDataItemById = function (e, t) {
                    for (var n = e.length - 1; n >= 0; n--) {
                        var i = e.getIndex(n);
                        if (i.id == t) return i;
                        if (i.children) {
                            var r = this.getDataItemById(i.children, t);
                            if (r) return r
                        }
                    }
                }, t.prototype.createDataItem = function () {
                    return new ge
                }, Object.defineProperty(t.prototype, "nodes", {
                    get: function () {
                        if (!this._nodes) {
                            var e = this.createNode();
                            e.applyOnClones = !0, this._disposers.push(e), this._nodes = new d.e(e), this._disposers.push(new d.c(this._nodes))
                        }
                        return this._nodes
                    }, enumerable: !0, configurable: !0
                }), Object.defineProperty(t.prototype, "links", {
                    get: function () {
                        if (!this._links) {
                            var e = this.createLink();
                            e.applyOnClones = !0, this._disposers.push(e), this._links = new d.e(e), this._disposers.push(new d.c(this._links))
                        }
                        return this._links
                    }, enumerable: !0, configurable: !0
                }), t.prototype.createNode = function () {
                    return new g
                }, t.prototype.createLink = function () {
                    return new c
                }, Object.defineProperty(t.prototype, "minRadius", {
                    get: function () {
                        return this.getPropertyValue("minRadius")
                    }, set: function (e) {
                        this.setPropertyValue("minRadius", e, !0)
                    }, enumerable: !0, configurable: !0
                }), Object.defineProperty(t.prototype, "maxRadius", {
                    get: function () {
                        return this.getPropertyValue("maxRadius")
                    }, set: function (e) {
                        this.setPropertyValue("maxRadius", e, !0)
                    }, enumerable: !0, configurable: !0
                }), Object.defineProperty(t.prototype, "colors", {
                    get: function () {
                        return this.getPropertyValue("colors")
                    }, set: function (e) {
                        this.setPropertyValue("colors", e, !0)
                    }, enumerable: !0, configurable: !0
                }), Object.defineProperty(t.prototype, "maxLevels", {
                    get: function () {
                        return this.getPropertyValue("maxLevels")
                    }, set: function (e) {
                        this.setPropertyValue("maxLevels", e, !0)
                    }, enumerable: !0, configurable: !0
                }), Object.defineProperty(t.prototype, "manyBodyStrength", {
                    get: function () {
                        return this.getPropertyValue("manyBodyStrength")
                    }, set: function (e) {
                        this.setPropertyValue("manyBodyStrength", e) && this.restartSimulation()
                    }, enumerable: !0, configurable: !0
                }), Object.defineProperty(t.prototype, "centerStrength", {
                    get: function () {
                        return this.getPropertyValue("centerStrength")
                    }, set: function (e) {
                        this.setPropertyValue("centerStrength", e) && this.restartSimulation()
                    }, enumerable: !0, configurable: !0
                }), t.prototype.createLegendMarker = function (e, t) {
                    e.children.each(function (n) {
                        var i = t.node;
                        n instanceof ve.a && n.cornerRadius(40, 40, 40, 40), n.defaultState.properties.fill = i.fill, n.defaultState.properties.stroke = i.stroke, n.defaultState.properties.fillOpacity = i.fillOpacity, n.defaultState.properties.strokeOpacity = i.strokeOpacity, n.fill = i.fill, n.stroke = i.stroke, n.fillOpacity = i.fillOpacity, n.strokeOpacity = i.strokeOpacity, void 0 == n.fill && (n.__disabled = !0);
                        var r = e.dataItem;
                        r.color = i.fill, r.colorOrig = i.fill, i.events.on("propertychanged", function (e) {
                            "fill" == e.property && (n.__disabled = !1, n.isActive || (n.fill = i.fill), n.defaultState.properties.fill = i.fill, r.color = i.fill, r.colorOrig = i.fill), "stroke" == e.property && (n.isActive || (n.stroke = i.stroke), n.defaultState.properties.stroke = i.stroke)
                        }, void 0, !1)
                    })
                }, t
            }(h.a);
        a.b.registeredClasses.ForceDirectedSeries = me, a.b.registeredClasses.ForceDirectedSeriesDataItem = ge;
        var xe = function (e) {
            function t() {
                return null !== e && e.apply(this, arguments) || this
            }

            return r.c(t, e), t
        }(u.b), be = function (e) {
            function t() {
                var t = e.call(this) || this;
                return t.className = "ForceDirectedTree", t.seriesContainer.isMeasured = !0, t.seriesContainer.layout = "absolute", t.applyTheme(), t
            }

            return r.c(t, e), t.prototype.createSeries = function () {
                return new me
            }, t.prototype.createDataItem = function () {
                return new xe
            }, t.prototype.feedLegend = function () {
                var e = this.legend;
                if (e) {
                    var t = [];
                    this.series.each(function (n) {
                        if (!n.hiddenInLegend) {
                            var i = n.dataItems;
                            if (1 == i.length) {
                                var r = n.dataItems.getIndex(0).children;
                                r.length > 0 && (i = r)
                            }
                            i.each(function (i) {
                                if (!i.hiddenInLegend) {
                                    t.push(i);
                                    var r = n.legendSettings;
                                    r && (r.labelText && (e.labels.template.text = r.labelText), r.itemLabelText && (e.labels.template.text = r.itemLabelText), r.valueText && (e.valueLabels.template.text = r.valueText), r.itemValueText && (e.valueLabels.template.text = r.itemValueText))
                                }
                            })
                        }
                    }), e.data = t, e.dataFields.name = "name"
                }
            }, t.prototype.applyInternalDefaults = function () {
                e.prototype.applyInternalDefaults.call(this), v.hasValue(this.readerTitle) || (this.readerTitle = this.language.translate("Force directed tree"))
            }, t
        }(u.a);
        a.b.registeredClasses.ForceDirectedTree = be, a.b.registeredClasses.ForceDirectedTreeDataItem = xe, window.am4plugins_forceDirected = i
    }
}, ["eUZ+"]);
//# sourceMappingURL=forceDirected.js.map